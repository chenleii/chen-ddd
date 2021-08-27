package com.chen.ddd.infrastructure.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;

/**
 * @author chen
 * @since 2017/10/28 19:11.
 */
public class RsaUtils {

    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 2048;
    /**
     * RSA默认加密明文大小
     * 最大加密分段为  KEY_SIZE / 8 - 11;
     */
    private static final int DEFAULT_ENCRYPT_BLOCK = KEY_SIZE / 8 - 11;
    /**
     * RSA默认解密密文大小
     * 最大解密分段为 KEY_SIZE / 8;
     */
    private static final int DEFAULT_DECRYPT_BLOCK = KEY_SIZE / 8;

    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * 随机生成密钥对
     *
     * @param filePath 生成密钥的路径
     * @param keySize  密钥长度
     */
    public static void genKeyPair(final String filePath, final int keySize) {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(keySize, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 得到公钥字符串
            String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            // 将密钥对写入到文件
            try (
                    BufferedWriter pubbw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + "/rsa_public_key_pkcs8_" + keySize + ".txt"), DEFAULT_CHARSET));
                    BufferedWriter pribw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + "/rsa_private_key_pkcs8_" + keySize + ".txt"), DEFAULT_CHARSET));
            ) {
                pubbw.write(publicKeyString);
                pribw.write(privateKeyString);
                pubbw.flush();
                pribw.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @return 公钥
     * @throws RuntimeException 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(final String publicKeyStr) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此加密算法");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("公钥非法");
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("公钥数据为空");
        }
    }

    /**
     * 从字符串中加载私钥
     *
     * @param privateKeyStr 私钥数据字符串
     * @return 私钥
     * @throws RuntimeException 加载公钥时产生的异常
     */
    public static RSAPrivateKey loadPrivateKeyByStr(final String privateKeyStr) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此加密算法");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("私钥非法");
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("私钥数据为空");
        }
    }

    /**
     * 获取私钥长度
     *
     * @param keyFactory keyFactory
     * @param key        私钥
     * @return 长度
     * @throws InvalidKeySpecException 无效的key
     */
    public static int privateKeyLength(final KeyFactory keyFactory, final Key key) throws InvalidKeySpecException {
        RSAPrivateKeySpec keySpec = keyFactory.getKeySpec(key, RSAPrivateKeySpec.class);
        BigInteger prime = keySpec.getModulus();
        // 转换为二进制，获取公钥长度
        return prime.bitLength();
    }

    /**
     * 获取公钥长度
     *
     * @param keyFactory keyFactory
     * @param key        公钥
     * @return 长度
     * @throws InvalidKeySpecException 无效的key
     */
    public static int publicKeyLength(final KeyFactory keyFactory, final Key key) throws InvalidKeySpecException {
        RSAPublicKeySpec keySpec = keyFactory.getKeySpec(key, RSAPublicKeySpec.class);
        BigInteger prime = keySpec.getModulus();
        // 转换为二进制，获取公钥长度
        return prime.bitLength();
    }

    /**
     * 数据分段加密解密
     *
     * @param data          数据
     * @param cipher        加密解密操作对象
     * @param segmentLength 分段大小
     * @return 分段加密解密后的字节数组
     * @throws BadPaddingException       数据损坏
     * @throws IllegalBlockSizeException 明文长度非法
     * @throws IOException               失败
     */
    private static byte[] dataSubsection(final byte[] data, final Cipher cipher, final int segmentLength) throws BadPaddingException, IllegalBlockSizeException, IOException {
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > segmentLength) {
                cache = cipher.doFinal(data, offSet, segmentLength);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * segmentLength;
        }
        byte[] output = out.toByteArray();
        out.close();
        return output;
    }

    /**
     * 公钥加密
     *
     * @param publicKeyStr  公钥字符串
     * @param plainTextData 明文数据
     * @return 加密内容经过base64编码的字符串
     */
    public static String encryptAsBase64(final String publicKeyStr, final byte[] plainTextData) {
        RSAPublicKey publicKey = RsaUtils.loadPublicKeyByStr(publicKeyStr);
        return RsaUtils.encryptAsBase64(publicKey, plainTextData);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyStr  公钥字符串
     * @param plainTextData 明文数据
     * @return 加密内容
     */
    public static byte[] encrypt(final String publicKeyStr, final byte[] plainTextData) {
        RSAPublicKey publicKey = RsaUtils.loadPublicKeyByStr(publicKeyStr);
        return RsaUtils.encrypt(publicKey, plainTextData);
    }

    /**
     * 公钥加密
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return 加密内容经过base64编码的字符串
     */
    public static String encryptAsBase64(final PublicKey publicKey, final byte[] plainTextData) {
        byte[] encryptByteArray = RsaUtils.encrypt(publicKey, plainTextData);
        return Base64.getEncoder().encodeToString(encryptByteArray);
    }

    /**
     * 公钥加密
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return 加密内容
     */
    public static byte[] encrypt(final PublicKey publicKey, final byte[] plainTextData) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 使用默认RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return RsaUtils.dataSubsection(plainTextData, cipher, RsaUtils.publicKeyLength(keyFactory, publicKey) / 8 - 11);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这样的填补");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("明文长度非法");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("明文数据已损坏");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("无效的key");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * 私钥解密
     *
     * @param privateKeyStr 私钥字符串
     * @param cipherData    密文数据
     * @return 解密内容的字符串格式
     */
    public static String decryptAsString(final String privateKeyStr, final byte[] cipherData) {
        return RsaUtils.decryptAsString(privateKeyStr, cipherData, null);
    }

    /**
     * 私钥解密
     *
     * @param privateKeyStr 私钥字符串
     * @param cipherData    密文数据
     * @param encode        文件编码
     * @return 解密内容的字符串格式
     */
    public static String decryptAsString(final String privateKeyStr, final byte[] cipherData, final String encode) {
        RSAPrivateKey privateKey = RsaUtils.loadPrivateKeyByStr(privateKeyStr);
        return RsaUtils.decryptAsString(privateKey, cipherData, encode);
    }


    /**
     * 私钥解密
     *
     * @param privateKeyStr 私钥字符串
     * @param cipherData    密文数据
     * @return 解密内容
     */
    public static byte[] decrypt(final String privateKeyStr, final byte[] cipherData) {
        RSAPrivateKey privateKey = RsaUtils.loadPrivateKeyByStr(privateKeyStr);
        return RsaUtils.decrypt(privateKey, cipherData);
    }

    /**
     * 私钥解密
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @param encode     文件编码
     * @return 解密内容的字符串格式
     */
    public static String decryptAsString(final PrivateKey privateKey, final byte[] cipherData, final String encode) {
        try {
            byte[] decryptByteArray = RsaUtils.decrypt(privateKey, cipherData);
            if (encode != null && !"".equals(encode)) {
                return new String(decryptByteArray, encode);
            } else {
                return new String(decryptByteArray, DEFAULT_CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这个编码");
        }
    }

    /**
     * 私钥解密
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 解密内容
     */
    public static byte[] decrypt(final PrivateKey privateKey, final byte[] cipherData) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 使用默认RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return RsaUtils.dataSubsection(Base64.getDecoder().decode(cipherData), cipher, RsaUtils.privateKeyLength(keyFactory, privateKey) / 8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这样的填补");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("密文长度非法");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("密文数据已损坏");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这个编码");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加密失败");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("无效的key");
        }
    }


    /**
     * 私钥加密
     *
     * @param privateKeyStr 私钥字符串
     * @param plainTextData 明文数据
     * @return 加密后内容base64编码后的字符串
     */
    public static String privateKeyEncryptAsBase64(final String privateKeyStr, final byte[] plainTextData) {
        RSAPrivateKey privateKey = RsaUtils.loadPrivateKeyByStr(privateKeyStr);
        return RsaUtils.privateKeyEncryptAsBase64(privateKey, plainTextData);
    }

    /**
     * 私钥加密
     *
     * @param privateKeyStr 私钥字符串
     * @param plainTextData 明文数据
     * @return 加密后内容
     */
    public static byte[] privateKeyEncrypt(final String privateKeyStr, final byte[] plainTextData) {
        RSAPrivateKey privateKey = RsaUtils.loadPrivateKeyByStr(privateKeyStr);
        return RsaUtils.privateKeyEncrypt(privateKey, plainTextData);
    }

    /**
     * 私钥加密
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     * @return 加密后内容base64编码后的字符串
     */
    public static String privateKeyEncryptAsBase64(final PrivateKey privateKey, final byte[] plainTextData) {
        byte[] encryptByteArray = RsaUtils.privateKeyEncrypt(privateKey, plainTextData);
        return Base64.getEncoder().encodeToString(encryptByteArray);
    }

    /**
     * 私钥加密
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     * @return 加密后内容
     */
    public static byte[] privateKeyEncrypt(final PrivateKey privateKey, final byte[] plainTextData) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 使用默认RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            return RsaUtils.dataSubsection(plainTextData, cipher, RsaUtils.privateKeyLength(keyFactory, privateKey) / 8 - 11);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这样的填补");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("密文长度非法");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("密文数据已损坏");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这个编码");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加密失败");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("无效的key");
        }
    }

    /**
     * 公钥解密
     *
     * @param publicKeyStr 公钥字符串
     * @param cipherData   密文数据
     * @return 解密后内容字符串形式
     */
    public static String publicKeyDecryptAsString(final String publicKeyStr, final byte[] cipherData) {
        return RsaUtils.publicKeyDecryptAsString(publicKeyStr, cipherData, null);
    }

    /**
     * 公钥解密
     *
     * @param publicKeyStr 公钥字符串
     * @param cipherData   密文数据
     * @param encode       编码
     * @return 解密后内容字符串形式
     */
    public static String publicKeyDecryptAsString(final String publicKeyStr, final byte[] cipherData, final String encode) {
        RSAPublicKey publicKey = loadPublicKeyByStr(publicKeyStr);
        return RsaUtils.publicKeyDecryptAsString(publicKey, cipherData, encode);
    }

    /**
     * 公钥解密
     *
     * @param publicKeyStr 公钥字符串
     * @param cipherData   密文数据
     * @return 解密后内容
     */
    public static byte[] publicKeyDecrypt(final String publicKeyStr, final byte[] cipherData) {
        RSAPublicKey publicKey = loadPublicKeyByStr(publicKeyStr);
        return RsaUtils.publicKeyDecrypt(publicKey, cipherData);
    }

    /**
     * 公钥解密
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @param encode     编码
     * @return 解密后内容字符串形式
     */
    public static String publicKeyDecryptAsString(final PublicKey publicKey, final byte[] cipherData, final String encode) {
        byte[] decryptByteArray = RsaUtils.publicKeyDecrypt(publicKey, cipherData);
        try {
            if (encode != null && !"".equals(encode)) {
                return new String(decryptByteArray, encode);
            } else {
                return new String(decryptByteArray, DEFAULT_CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这个编码");
        }
    }

    /**
     * 公钥解密
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @return 解密后内容
     */
    public static byte[] publicKeyDecrypt(final PublicKey publicKey, final byte[] cipherData) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 使用默认RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            return RsaUtils.dataSubsection(Base64.getDecoder().decode(cipherData), cipher, RsaUtils.publicKeyLength(keyFactory, publicKey) / 8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这样的填补");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new RuntimeException("明文长度非法");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException("明文数据已损坏");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("无效的key");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * 签名算法
     */
    public static final String MD5_WITH_RSA = "MD5withRSA";
    public static final String SHA1_WITH_RSA = "SHA1withRSA";
    public static final String SHA256_WITH_RSA = "SHA256withRSA";
    public static final String SHA384_WITH_RSA = "SHA384withRSA";
    public static final String SHA512_WITH_RSA = "SHA512withRSA";

    private static final String DEFAULT_RSA_SIGN_TYPE = SHA256_WITH_RSA;

    /**
     * RSA签名
     *
     * @param content       待签名数据
     * @param privateKeyStr 商户私钥字符串
     * @param encode        字符集编码
     * @param rsaSignType   签名类型
     * @return 签名值
     */
    public static String sign(final String content, final String privateKeyStr, final String encode, final String rsaSignType) {
        try {
            RSAPrivateKey privateKey = loadPrivateKeyByStr(privateKeyStr);
            Signature signature;
            if (rsaSignType != null && !"".equals(rsaSignType)) {
                signature = Signature.getInstance(rsaSignType);
            } else {
                signature = Signature.getInstance(DEFAULT_RSA_SIGN_TYPE);
            }
            signature.initSign(privateKey);
            if (encode != null && !"".equals(encode)) {
                signature.update(content.getBytes(encode));
            } else {
                signature.update(content.getBytes(DEFAULT_CHARSET));
            }
            byte[] signed = signature.sign();
            return Base64.getEncoder().encodeToString(signed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此加密算法");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这个编码");
        } catch (SignatureException e) {
            e.printStackTrace();
            throw new RuntimeException("签名异常");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("无效的key");
        }
    }

    public static String sign(final String content, final String privateKey) {
        return sign(content, privateKey, null, null);
    }

    public static String sign(final String content, final String privateKey, final String rsaSignType) {
        return sign(content, privateKey, null, rsaSignType);
    }

    /**
     * * RSA验签名检查
     *
     * @param content      待签名数据
     * @param sign         签名值
     * @param publicKeyStr 分配给开发商公钥
     * @param encode       字符集编码
     * @param rsaSignType  签名类型
     * @return 是否
     */
    public static boolean doCheck(final String content, final String sign, final String publicKeyStr, final String encode, final String rsaSignType) {
        try {
            RSAPublicKey publicKey = loadPublicKeyByStr(publicKeyStr);
            Signature signature;
            if (rsaSignType != null && !"".equals(rsaSignType)) {
                signature = Signature.getInstance(rsaSignType);
            } else {
                signature = Signature.getInstance(DEFAULT_RSA_SIGN_TYPE);
            }
            signature.initVerify(publicKey);
            if (encode != null && !"".equals(encode)) {
                signature.update(content.getBytes(encode));
            } else {
                signature.update(content.getBytes(DEFAULT_CHARSET));
            }
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("无此加密算法");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("没有这个编码");
        } catch (SignatureException e) {
            e.printStackTrace();
            throw new RuntimeException("签名异常");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("无效的key");
        }
    }

    public static boolean doCheck(final String content, final String sign, final String publicKey) {
        return doCheck(content, sign, publicKey, null, null);
    }

    public static boolean doCheck(final String content, final String sign, final String publicKey, final String rsaSignType) {
        return doCheck(content, sign, publicKey, null, rsaSignType);
    }


}

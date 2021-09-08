CREATE TABLE IF NOT EXISTS `order`
(
    `id`                    bigint(20) unsigned     NOT NULL COMMENT '订单ID',
    `buyer_id`              bigint(20) unsigned     NOT NULL COMMENT '买家ID',
    `total_price_currency`  varchar(20)             NOT NULL COMMENT '订单总价货币',
    `total_price`           decimal(10, 2) unsigned NOT NULL COMMENT '订单总价',
    `status`                int(10) unsigned        NOT NULL COMMENT '订单状态',
    `shipping_address`      json                    NOT NULL COMMENT '收货地址',
    `logistics_tracking_no` varchar(100)            DEFAULT NULL COMMENT '物流快递单号',
    `logistics_status`      int(10) unsigned        NOT NULL COMMENT '物流状态',
    `created_at`            bigint(20) unsigned     NOT NULL COMMENT '账户创建于',
    `updated_at`            bigint(20) unsigned     NOT NULL COMMENT '账户更新于',
    PRIMARY KEY (`id`),
    KEY `idx_orderbuyer_id` (`buyer_id`),
    KEY `idx_ordercreated_at` (`created_at`),
    KEY `idx_orderupdated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';

CREATE TABLE IF NOT EXISTS `order_item`
(
    `id`                  bigint(20) unsigned     NOT NULL COMMENT '订单项ID',
    `order_id`            bigint(20) unsigned     NOT NULL COMMENT '订单ID',
    `buyer_id`            bigint(20) unsigned     NOT NULL COMMENT '买家ID',
    `product_id`          bigint(20) unsigned     NOT NULL COMMENT '商品ID',
    `product_title`       varchar(128)            NOT NULL COMMENT '商品标题',
    `quantity`            int(10)                 NOT NULL COMMENT '购买数量',
    `unit_price_currency` varchar(20)             NOT NULL COMMENT '订单单价货币',
    `unit_price`          decimal(10, 2) unsigned NOT NULL COMMENT '订单单价',
    `product_snapshot`    json                    NOT NULL COMMENT '商品快照',
    `created_at`          bigint(20) unsigned     NOT NULL COMMENT '账户创建于',
    `updated_at`          bigint(20) unsigned     NOT NULL COMMENT '账户更新于',
    PRIMARY KEY (`id`),
    KEY `idx_order_itemorder_id` (`order_id`),
    KEY `idx_order_itembuyer_id` (`buyer_id`),
    KEY `idx_order_itemproduct_id` (`product_id`),
    KEY `idx_order_itemcreated_at` (`created_at`),
    KEY `idx_order_itemupdated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单项表';

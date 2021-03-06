# language: zh-CN

功能: 订单功能（订单生命周期）

  @transaction
  场景: 买家购买商品流程
    假如已有商品
    当买家下单
    那么订单状态为待支付
    当买家支付订单
    那么订单状态为待发货
    当订单发货
    那么订单状态为已发货
    当买家确认收货
    那么订单状态为已完结

  @transaction
  场景: 订单物流流程
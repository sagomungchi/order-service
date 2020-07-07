package com.pcbang.order.mvp.domain.cart;

public enum OrderState {
    Cancel, // 취소
    OrderWait, // 주문대기
    Ordered, // 주문완료
    Paid,
    Prepared,
    Shipping,
    Rejected
}

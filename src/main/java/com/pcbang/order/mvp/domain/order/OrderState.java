package com.pcbang.order.mvp.domain.order;

public enum OrderState {
    Cancel, // 취소
    OrderWait, // 주문대기
    Ordered, // 주문완료
    Paid, // 결제 완료
    Prepared, // 상품 준비중
    Shipping, // 배송
    Rejected // 거절
}

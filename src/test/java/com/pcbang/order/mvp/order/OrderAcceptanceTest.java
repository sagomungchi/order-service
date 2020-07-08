package com.pcbang.order.mvp.order;

import com.pcbang.order.mvp.domain.order.Order;
import com.pcbang.order.mvp.domain.order.dto.OrderLineInfo;
import com.pcbang.order.mvp.domain.order.dto.OrderRequests;
import com.pcbang.order.mvp.domain.item.Item;
import com.pcbang.order.mvp.domain.item.dto.ItemInfo;
import com.pcbang.order.mvp.item.ItemRepository;
import com.pcbang.order.mvp.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.greaterThan;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    private String orderId;

    @BeforeEach
    @DisplayName("주문 등록 테스트")
    void createOrder() {

        OrderRequests orderRequests = new OrderRequests();

        ItemInfo itemInfo = ItemInfo.builder()
                .name("테스트 상품1")
                .description("테스트 상품입니다.")
                .distributor("청주지사")
                .inventory(3)
                .price(10000)
                .build();

        ItemInfo itemInfo2 = ItemInfo.builder()
                .name("테스트 상품2")
                .description("테스트 상품입니다.")
                .distributor("청주지사")
                .inventory(3)
                .price(10000)
                .build();

        Item item1 = itemRepository.save(itemInfo.toEntity());
        Item item2 = itemRepository.save(itemInfo2.toEntity());

        OrderLineInfo orderLine = new OrderLineInfo(item1, 1);
        OrderLineInfo orderLine2 = new OrderLineInfo(item2, 1);

        orderRequests.addRequest(orderLine);
        orderRequests.addRequest(orderLine2);

        Order order = new Order(orderRequests.toEntity(), LocalDateTime.now());

        orderRepository.save(order);
        order.getOrderLines().toString();

//        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
//                .uri("/carts/")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromFormData("requests", String.valueOf(orderRequests)))
//                .exchange()
//                .expectStatus()
//                .isCreated()
//                .expectHeader().valueMatches("location", "/carts/[1-9]+[0-9]*");
//
//        orderId = AcceptanceTestUtils.extractDomainIdFromCreatedResourceAddress(responseSpec);
    }

    @Test
    @DisplayName("주문 전체 조회 테스트")
    void showAllItems(){
        webTestClient.get()
                .uri("/carts/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.length()", greaterThan(1));
    }

//    @Test
//    @DisplayName("콘테스트 수정 테스트")
//    void updateItem(){
//        webTestClient.post()
//                .uri("/items/" + itemId)
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromFormData("name", "신라면")
//                        .with("description", "1박스")
//                        .with("distributor", "청주지사")
//                        .with("price", String.valueOf(10000))
//                        .with("inventory", String.valueOf(10)))
//                .exchange()
//                .expectStatus()
//                .isNoContent();
//
//        webTestClient.get()
//                .uri("/items/" + itemId)
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBody()
//                .jsonPath("$.name").isEqualTo("신라면");
//    }
//
//    @Test
//    @DisplayName("아이템 삭제 테스트")
//    void deleteItem(){
//        webTestClient.delete()
//                .uri("/items/" + itemId)
//                .exchange()
//                .expectStatus()
//                .isNoContent();
//
//        webTestClient.get()
//                .uri("/items/" + itemId)
//                .exchange()
//                .expectStatus()
//                .isNotFound();
//    }
}
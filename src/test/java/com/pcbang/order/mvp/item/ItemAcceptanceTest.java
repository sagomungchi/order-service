package com.pcbang.order.mvp.item;

import com.pcbang.order.mvp.AcceptanceTestUtils;
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

import static org.hamcrest.Matchers.greaterThan;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    private String itemId;

    @BeforeEach
    @DisplayName("상품 등록 테스트")
    void setUp() {
        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/items")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromFormData("name", "떡볶이")
                        .with("description", "밀떡")
                        .with("distributor", "청주지사")
                        .with("price", String.valueOf(10000))
                        .with("inventory", String.valueOf(10)))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().valueMatches("location", "/items/[1-9]+[0-9]*");

        itemId = AcceptanceTestUtils.extractDomainIdFromCreatedResourceAddress(responseSpec);
    }

    @Test
    @DisplayName("상품 전체 조회 테스트")
    void showAllItems(){
        webTestClient.get()
                .uri("/items")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.length()", greaterThan(1));
    }

    @Test
    @DisplayName("특정 상품 조회 테스트")
    void showItem(){
        webTestClient.get()
                .uri("/items/" + itemId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("떡볶이");
    }

    @Test
    @DisplayName("콘테스트 수정 테스트")
    void updateItem(){
        webTestClient.post()
                .uri("/items/" + itemId)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromFormData("name", "신라면")
                        .with("description", "1박스")
                        .with("distributor", "청주지사")
                        .with("price", String.valueOf(10000))
                        .with("inventory", String.valueOf(10)))
                .exchange()
                .expectStatus()
                .isNoContent();

        webTestClient.get()
                .uri("/items/" + itemId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("신라면");
    }

    @Test
    @DisplayName("아이템 삭제 테스트")
    void deleteItem(){
        webTestClient.delete()
                .uri("/items/" + itemId)
                .exchange()
                .expectStatus()
                .isNoContent();

        webTestClient.get()
                .uri("/items/" + itemId)
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
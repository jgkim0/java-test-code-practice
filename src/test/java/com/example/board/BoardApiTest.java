package com.example.board;


import com.example.board.dto.AddPostRequest;
import com.example.board.dto.UpdatePostRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardApiTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void registPost() {
        final AddPostRequest request1 = postRegistRequestMake();
        final AddPostRequest request2 = postRegistRequestMake();
        final AddPostRequest request3 = postRegistRequestMake();

        // API 요청
        final ExtractableResponse<Response> response1 = postRegisterRequest(request1);
        final ExtractableResponse<Response> response2 = postRegisterRequest(request2);
        final ExtractableResponse<Response> response3 = postRegisterRequest(request3);

        assertThat(response1.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response2.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response3.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    void viewPost() {

        makePost3();

        ExtractableResponse<Response> response = 게시글_조회_결과확인(2L);

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    void viewAllPost() {
        makePost3();

        ExtractableResponse<Response> response = 게시글_전체_조회();

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    void updatePost() {

        makePost3();

        UpdatePostRequest request = postUpdateRequestMake(1L);

        게시글_수정_요청(request);

        ExtractableResponse<Response> response = 게시글_전체_조회();

        assertThat(response.statusCode()).isEqualTo(200);
    }


    @Test
    void deletePost() {

        makePost3();

        게시글_삭제_요청(2L);

        ExtractableResponse<Response> response = 게시글_전체_조회();

        assertThat(response.statusCode()).isEqualTo(200);


    }

    private void makePost3() {
        final AddPostRequest request1 = postRegistRequestMake();
        final AddPostRequest request2 = postRegistRequestMake();
        final AddPostRequest request3 = postRegistRequestMake();
        postRegisterRequest(request1);
        postRegisterRequest(request2);
        postRegisterRequest(request3);
    }


    // 작성 api
    public ExtractableResponse<Response> postRegisterRequest(AddPostRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/board/add")
                .then()
                .log().all().extract();
    }

    // 게시글 작성
    private static AddPostRequest postRegistRequestMake() {
        String title = "히히";
        String writer = "야호";
        String context = "r u happy with this?";

        return new AddPostRequest(title, writer, context);
    }


    // 게시글 상세 조회
    ExtractableResponse<Response> 게시글_조회_결과확인(Long id) {
        return RestAssured.given().log().all()
                        .when()
                        .get("/board/view/" + id)
                        .then()
                        .log().all().extract();
    }


    // 게시판 조회
    public ExtractableResponse<Response> 게시글_전체_조회() {
        return RestAssured.given().log().all()
                .when()
                .get("/board/findAll")
                .then()
                .log().all().extract();
    }

    // 게시판 수정

    public void 게시글_수정_요청(UpdatePostRequest request) {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/board/update")
                .then()
                .log().all().extract();
    }

    private static UpdatePostRequest postUpdateRequestMake(Long id) {

        String title = "히히22222";
        String context = "i can do it 🤸‍♀️🤸‍♀️";

        return new UpdatePostRequest(id, title, context);
    }

    // 게시글 삭제
    public void 게시글_삭제_요청(Long id) {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(id)
                .when()
                .post("/board/delete")
                .then()
                .log().all().extract();
    }

}

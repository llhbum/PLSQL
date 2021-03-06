public class Chapter5_구분자와식별자 {
    /*
    Chapter5_구분자와식별자

    5.1 구분자
        구분자 목록
        := (대입연산자)
        => (저장 서브프로그램의 매개변수명을 값과 연관시키는 연산자)
        % (속성지시자) - 커서 상태 변수에 사용 또는 앵커 타입 지시자
        || (연결연산자)
        .. (범위연산자)
        <>, !=, ~=, ^= (부등 관계 연산자)
        @ (원격 접근 지시자(DB LINK에 사용))

    5.2 식별자
        - 식별자는 PL/SQL 프로그램 구성 요소의 이름을 의미한다.
        식별자 목록
        커서 : DB에 저장된 값을 접근하기 위한 구조
        레이블 : PL/SQL의 블록 또는 문장에 대한 이름
        서브프로그램 : 호출 가능한 함수와 프로시저
        트리거 : 특정 이벤트 발생 시 데이터베이스에 의해 자동적으로 호출되는 저장 서브프로그램
        패키지 : 타입, 상수, 변수, 예외, 커서, 서브프로그램 등을 모듈화한 저장 서브프로그램
        객체 타입 : 관계형 DB에 객체 지향 프로그래밍을 가능하게 하는 프로그램 요소
        예약어 : 내부적으로 사용하기 위해 예약된 단어
        키워드 : 오라클 PL/SQL 문법에서 사용되는 핵심 단어

    5.3 식별자의 유효 범위
        - 모든 식별자는 유효 범위를 가진다. 하나의 PL/SQL 블록 내에서 선언된 식별자는 해당 블록 내에서만 유효하며, 자신의 유효 범위를 벗어나면 인식이 불가능하다.
        [예시]
        DECLARE
            c_table_name CONSTANT STRING(30) := 'EMP" ;
        BEGIN
            DBMS_OUTPUT.PUT_LINE('테이블' || c_table_name || '의 파티션 개수 출력');
            DECLARE -- 중첩된 블록
                v_num_partitions NUMBER;
            BEGIN
                SELECT COUNT(*)
                    INTO v_num_partitions
                FROM USER_TAB_PARTITIONS
                WHERE TABLE_NAME = c_table_name
                DBMS_OUTPUT.PUT_LINE('파티션 개수 = ' || v_num_partitions); -- *에러 : 유효범위를 벗어나서 사용됨 *
            END;
        END;

        - 식별자명 충돌 시의 유효 범위
        [예시]
        DECLARE
        BEGIN
            DECLARE
            BEGIN
            END
        END;
     */
}

public class Chapter11_컬렉션 {
    /*
    Chapter11_컬렉션
        [개념]
            - 컬렉션은 동일한 데이터 타입이 반복되는 데이터를 저장하는 자료구조, 레코드는 서로 다른 데이터 타입의 데이터를 모아 놓은 자료구조

        [종류]
            - Associative Array (키-value) : 해시테이블, 비순서 테이블
            - VARRAY            (배열 - 크기지정) : 배열, 벡터
            - Nested Table      (배열 - 크기미지정) : 집합, 중복 허용 집합

        11.2 Associative Array
            [개념]
                - Associative Array는 컬렉션 항목의 개수를 사전에 지정하지 않는다.
                - Associative Array의 개별 항목을 유일하게 식별하는 인덱스(key)로는 정수와 문자열 둘 중 하나를 사용할 수 있다.

            [구조]
                TYPE 타입명 IS TABLE OF 데이터타입 INDEX BY 인덱스데이터타입;
                변수명 타입명

            [예제]
                - BULK COLLECT는 여러 건의 SELECT 결과를 Associative Array 변수에 넣는다.
                    DECLARE
                        TYPE string_array IS TABLE OF VARCHAR2(100) INDEX BY PLS_INTEGER;
                        v_arr string_array;
                    BEGIN
                        -- 테이블 emp의 모든 로우의 ename을 Associative Array 컬렉션에 한 번에 적재한다.
                        SELECT ename
                            BULK COLLECT INTO v_arr
                        FROM emp;
                        DBMS_OUTPUT.PUT_LINE('Associative Array 컬렉션 건수 = ' || v_arr.COUNT);
                    END;

            - 함수의 반환형으로 Associative Array 사용
            - 값이 할당되지 않은 Associative Array 항목은 참조할 수 없다.

        11.3 VARRAY(Variable-SIZE Array)
            [개념]
                - VARRAY 변수의 선언 시에는 배열의 크기를 지정해야 한다, 이 크기는 배열이 가질 수 있는 최대 크기를 의미한다.
            [구조]
                TYPE 타입명 IS VARRAY(크기) OF 데이터타입;
                변수명 타입명;

        11.4 Nested Table
            [개념]
                - Nested Table은 순서가 고정되어 있지 않고 크기도 고정되지 않은 데이터의 집합을 저장하는데 적합한 컬렉션이다. 컬렉션의 인덱스는 1부터 시작하여 순차적으로 증가한다.
                - Nested Table은 값을 저장하거나 조회할 때 저장된 항목의 순서(특정 인덱스에 저장된 항목)가 항상 동일함을 보장하지 않는다.

            [차이점]
                - VARRAY는 배열이 꽉 차있는 밀집형이지만 Nested Table은 중간에 빈 항목이 있을 수 있다.

            [구조]
                TYPE 타입명 IS TABLE OF 데이터타입;
                변수명 타입명;
            [예제]
                - Nested Table 컬렉션 사용 (타입명.EXTEND; 로 증가시켜야함)
                - DELETE 메소드를 사용하여 삭제한 Nested Table 항목은 참조할 수 없다.
                - BULK COLLECT는 여러 건의 SELECT 결과를 Nested Table변수에 넣는다.

        11.5 컬렉션 생성자
            [개념]
                - 세 가지 컬렉션 중에서 VARRAY와 Nested Table로 선언된 변수는 초기화하지 않으면 NULL이다.
                - Associative Array는 초기화가 필요하지 않다.
                - VARRAY와 Nested Table 컬렉션 변수는 사용하기 전에 반드시 초기화해야 한다.
                - 컬렉션 변수의 초기화를 위해서는 생성자를 사용해야 하는데, 생성자는 컬렉션 타입과 동일한 이름을 가지는, 시스템이 자동으로 생성해 주는 함수이다.

            [구조]
                컬렉션타입([값 [, 값 ... ] ] )

            [예제]
                 - 컬렉션 생성자
                    DECLARE
                        TYPE string_array IS TABLE OF VARCHAR2(100);
                        v_arr1 string_array := string_array(); -- 변수 선언 시 빈 컬렉션으로 초기화
                        v_arr2 string_array;
                    BEGIN
                        -- 실행 시 네개의 항목을 가지는 컬렉션으로 초기화
                        v_arr2 := string_array('사과', '수박', '망고', '배');
                        DBMS_OUTPUT.PUT_LINE('v_arr1.COUNT = ' || v_arr1.COUNT);
                        DBMS_OUTPUT.PUT_LINE('v_arr2.COUNT = ' || v_arr2.COUNT);
                    END;

                        - Associative Array는 초기화 없이도 컬렉션으로 사용할 수 있어서 생성자가 필요하지 않을뿐더러, 생성자가 존재하지 않는다.

        11.6 컬렉션 연산
            11.6.1 컬렉션 변수 간의 할당
                [예제]
                    - 11-14, 동일 타입의 컬렉션 변수 간에는 할당 연산자를 사용하여 값을 복사할 수 있다.
                    - 11-15, 구조가 동일하더라도 타입명이 다르면 할당 연산이 불가능하다.

            11.6.2 컬렉션 비교
            11.6.3 컬렉션 메소드
                [개념]
                    - 컬렉션에 대한 정보를 얻거나 컬렉션에 대한 변경을 위해서 PL/SQL 내장 컬렉션 메소드가 제공된다.
                [구조]
                    - 컬렉션변수.컬렉션메소드(파라미터)

        11.7 다차원 컬렉션
            [예제]
                - 11-18, 다차원 컬렉션을 지원하지는 않지만 유사하게 사용할 수는 있다.

        11.8 배열 처리
            [개념]
                - PL/SQL의 배열 처리는 BULK COLLECT 또는 FORALL 키워드를 사용하여 하나의 SQL문으로 여러 건의 데이터를 한 번에 처리할 수 있도록 하는 기능이다.

            [종류]
                - BULK COLLECT : SELECT문이 서버에서 실행되어 여러 건의 결과가 추출되면 결과 로우를 한 번에 클라이언트로 회신하여 배열에 저장 : 테이블 -> 컬렉션
                - FORALL : DML 처리할 여러 건의 데이터를 배열에 담아 서버로 한 번에 전송하여 서버에서 한 번에 실행 : 컬렉션 -> 테이블

     */
}

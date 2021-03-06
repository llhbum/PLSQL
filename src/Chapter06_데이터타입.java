public class Chapter06_데이터타입 {
    /*
    Chapter6_데이터타입
        [개념]
            - 데이터 타입은 데이터의 저장 포맷, 저장 시의 제약 사항, 저장 가능한 값의 유효 범위를 지정하기 위해 사용되는 제약 조건이다.

        [PL/SQL에서 사용 가능한 데이터 타입]
            LOB : BLOB, CLOB, NCLOB, BFILE
            ROWID : ROWID, UROWID
            PL/SQL 전용타입
                - 숫자 : PLS_INTEGER
            사용자 정의 서브타입
                - 기존의 데이터 타입에 제약 조건을 추가한 데이터 타입
            객체 타입
                - 속성과 메소드로 구성되는 사용자 정의 객체 타입, ADT(Abstract Data Type)라고도 함

        6.1 문자형 데이터 타입
            [구조]
                데이터타입 [ ( 크기 [ BYTE | CHAR ] ) ]

        6.5 사용자 정의 서브타입
            [개념]
                - 사용자 정의 서브타입은 베이스 타입(기존 데이터 타입)에 제약 조건을 추가하여 이를 새로운 데이터 타입으로 정의할 수 있도록 해준다.
            [종류]
                - 제약 없는 서브타입
                    : 베이스 타입과 동일한 데이터를 가질 수 있는 타입 실질적으로 베이스 타입의 다른 이름
                    [구조]
                        SUBTYPE 서브타입명 IS 베이스타입

                - 제약을 가진 서브타입
                    : 베이스 타입의 부분 집합이 되는 서브타입, 베이스 타입에 크기, 정밀도, 범위에 대한 제약을 주어 생성한다.
                    [구조]
                        SUBTYPE 서브타입명 IS 베이스타입
                            [ { 정밀도 [, 스케일] | RANGE 최솟값 .. 최댓값 } ] [ NOT NULL ]
            [예제]
                SUBTYPE 정수 IS PLS_INTEGER -- 제약 없는 서브타입
                SUBTYPE 이름 IS VARCHAR2(20 CHAR) NOT NULL; -- 크기와 NOT NULL 제약을 가진 서브타입
                v_name 이름 := 'SCOTT' ; -- NOT NULL이므로 초깃값을 지정하지 않으면 오류가 발생함

                SUBTYPE 등급 IS PLS_INTEGER RANGE 1000..9999; -- 범위 제약을 가지는 서브타입(네 자리 자연수만 유효)

        6.8 앵커(%)를 사용한 데이터 타입 지정
            [개념]
                - 차후에 참조되는 타입이 변경되더라도 이를 참조하는 쪽은 소스 코드를 수정하지 않아도 된다는 장점이 있는 데이터 타입
            [종류]
                - %TYPE
                    : 다른 변수 또는 칼럼의 데이터 타입과 동일한 타입을 지정할 때 사용한다.
                - %ROWTYPE
                    : 테이블 또는 커서의 로우와 동일한 구조를 가지는 레코드를 지정한다.

        6.9 스칼라 데이터 타입과 컴포지트 데이터 타입
            [개념]
                - 데이터 타입 중에서 어떤 것은 단 하나의 값만을 저장할 수 있고, 어떤 것은 동일한 데이터 타입의 값을 여러 개 저장하거나 서로 다른 데이터 타입의 값들을 여러 개 저장할 수도 있다.
                하나의 값만을 저장할 수 있는 데이터 타입을 스칼라 데이터타입, 두 개 이상 값을 저장할 수 있는 데이터 타입을 컴포지트 데이터 타입이라고 한다.
            [종류]
                - 스칼라 데이터 타입 : 오직 하나의 값만을 가진다 EX) VARCHAR2, NUMBER, DATE, BOOLEAN
                - 컴포지트 데이터 타입 : 내부적으로 여러 개의 구성 항목을 가질 수 있다. EX) Associative Array, VARRAY, NESTED Table, Record
     */
}

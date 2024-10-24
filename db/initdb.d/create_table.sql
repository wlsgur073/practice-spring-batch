CREATE TABLE `member`
(
    `member_id`   varchar(20) NOT NULL COMMENT '사용자 ID',
    `member_name` varchar(50) NOT NULL COMMENT '사용자 이름',
    `status`      varchar(10) NOT NULL COMMENT '상태',
    `phone`       varchar(50)          DEFAULT NULL COMMENT '연락처',
    `meta`        JSON                 DEFAULT NULL COMMENT '메타 정보, JSON',
    `created_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at` timestamp            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
    PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';

CREATE TABLE `package`
(
    `package_seq`  int         NOT NULL AUTO_INCREMENT COMMENT '패키지 순번',
    `package_name` varchar(50) NOT NULL COMMENT '패키지 이름',
    `count`        int                  DEFAULT NULL COMMENT '이용권 수, NULL인 경우 무제한',
    `period`       int                  DEFAULT NULL COMMENT '기간(일), NULL인 경우 무제한',
    `created_at`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`  timestamp            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
    PRIMARY KEY (`package_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='패키지';

CREATE TABLE `pass`
(
    `pass_seq`        int         NOT NULL AUTO_INCREMENT COMMENT '이용권 순번',
    `package_seq`     int         NOT NULL COMMENT '패키지 순번',
    `member_id`       varchar(20) NOT NULL COMMENT '사용자 ID',
    `status`          varchar(10) NOT NULL COMMENT '상태',
    `remaining_count` int                  DEFAULT NULL COMMENT '잔여 이용권 수, NULL인 경우 무제한',
    `started_at`      timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`        timestamp            DEFAULT NULL COMMENT '종료 일시, NULL인 경우 무제한',
    `expired_at`      timestamp            DEFAULT NULL COMMENT '만료 일시',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`     timestamp            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
    PRIMARY KEY (`pass_seq`),
    FOREIGN KEY (`package_seq`) REFERENCES `package`(`package_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`) ON DELETE CASCADE ON UPDATE CASCADE  -- member_id를 FK로 설정
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='이용권';

CREATE TABLE `booking`
(
    `booking_seq`  int         NOT NULL AUTO_INCREMENT COMMENT '예약 순번',
    `pass_seq`     int         NOT NULL COMMENT '이용권 순번',
    `member_id`    varchar(20) NOT NULL COMMENT '사용자 ID',
    `status`       varchar(10) NOT NULL COMMENT '상태',
    `used_pass`    BOOLEAN     NOT NULL DEFAULT 0 COMMENT '이용권 사용 여부',
    `attended`     BOOLEAN     NOT NULL DEFAULT 0 COMMENT '출석 여부',
    `started_at`   timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`     timestamp   NOT NULL COMMENT '종료 일시',
    `cancelled_at` timestamp            DEFAULT NULL COMMENT '취소 일시',
    `created_at`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`  timestamp            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
    PRIMARY KEY (`booking_seq`),
    FOREIGN KEY (`pass_seq`) REFERENCES `pass`(`pass_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`) ON DELETE CASCADE ON UPDATE CASCADE  -- member_id를 FK로 설정
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='예약';


-- 인덱스 생성 구문 추가
CREATE INDEX idx_pass_package_seq ON pass(package_seq);
CREATE INDEX idx_booking_pass_seq ON booking(pass_seq);
CREATE INDEX idx_pass_member_id ON pass(member_id);
CREATE INDEX idx_booking_member_id ON booking(member_id);
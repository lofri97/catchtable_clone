CREATE TABLE `announcement` (
  `id` int PRIMARY KEY,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `terms` (
  `id` int PRIMARY KEY,
  `type` ENUM ('TERMS_OF_USE', 'PRIVACY_POLICY', 'LOCATION_INFORMATION') NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `user_terms` (
  `id` bigint PRIMARY KEY,
  `user_id` bigint,
  `term_id` int NOT NULL
);

CREATE TABLE `user` (
  `id` bigint PRIMARY KEY,
  `email` varchar(255) UNIQUE NOT NULL COMMENT '로그인 email',
  `password` char NOT NULL,
  `nickname` varchar(255) UNIQUE NOT NULL,
  `description` varchar(255),
  `gender` ENUM ('MALE', 'FEMALE'),
  `contact` varchar(255) UNIQUE NOT NULL,
  `contact_verified` bool NOT NULL,
  `image_id` bigint,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `user_sns` (
  `id` bigint,
  `type` ENUM ('INSTAGRAM', 'X', 'YOUTUBE', 'BLOG') NOT NULL,
  `link` varchar(255) NOT NULL
);

CREATE TABLE `user_block` (
  `user_id` bigint,
  `block_ids` json
);

CREATE TABLE `user_follower` (
  `user_id` bigint,
  `follower_id` bigint NOT NULL
);

CREATE TABLE `user_preference_price` (
  `user_id` bigint UNIQUE,
  `minimum_price` int NOT NULL,
  `maximum_price` int NOT NULL,
  `created_at` datetime NOT NULL,
  `modified_at` datetime NOT NULL
);

CREATE TABLE `user_anniversary` (
  `user_id` bigint,
  `type` ENUM ('BIRTHDAY', 'WEDDING', 'FIRST_MET', 'ETC') NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255),
  `created_at` datetime DEFAULT (now()),
  `modified_at` datetime DEFAULT (now())
);

CREATE TABLE `user_preference_food` (
  `user_id` bigint,
  `food_types` json NOT NULL COMMENT 'food_type.type arr'
);

CREATE TABLE `user_service_notification` (
  `id` bigint PRIMARY KEY,
  `user_id` bigint NOT NULL,
  `type` ENUM ('RESERVATION', 'RESERVATION_OPEN', 'ACTIVITY') NOT NULL,
  `checked` bool NOT NULL DEFAULT false,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `user_bookmark` (
  `user_id` bigint,
  `restaurant_id` bigint,
  `note` varchar(255),
  PRIMARY KEY (`user_id`, `restaurant_id`)
);

CREATE TABLE `user_restaurant_coupon` (
  `user_id` bigint,
  `coupon_id` bigint NOT NULL,
  `used` bool NOT NULL DEFAULT (false)
);

CREATE TABLE `collection` (
  `id` int PRIMARY KEY,
  `user_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255),
  `public` bool NOT NULL DEFAULT (true)
);

CREATE TABLE `collection_restaurant` (
  `collection_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  `restaurant_id` bigint NOT NULL
);

CREATE TABLE `user_advertisement_notification` (
  `id` bigint PRIMARY KEY,
  `user_id` bigint NOT NULL,
  `type` ENUM ('PUSH', 'SMS', 'EMAIL') NOT NULL,
  `checked` bool NOT NULL DEFAULT false
);

CREATE TABLE `food_type` (
  `type` varchar(255) PRIMARY KEY
);

CREATE TABLE `comment` (
  `id` bigint PRIMARY KEY,
  `review_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `parent_id` bigint,
  `content` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `user_like` (
  `user_id` bigint NOT NULL,
  `type` ENUM ('REVIEW', 'COMMENT') NOT NULL,
  `object_id` bigint NOT NULL COMMENT 'comment.id or review.id'
);

CREATE TABLE `review` (
  `id` bigint PRIMARY KEY,
  `user_id` bigint NOT NULL,
  `restaurant_id` bigint NOT NULL,
  `visit_date` date NOT NULL,
  `visit_hour` ENUM ('LUNCH', 'DINNER') NOT NULL,
  `content` varchar(255) NOT NULL,
  `rate` json COMMENT 'FOOD: int, SERVICE: int, MOOD: int',
  `hashtags` json COMMENT 'Hashtag array',
  `image_ids` json COMMENT 'image id array',
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `restaurant` (
  `id` bigint PRIMARY KEY,
  `admin_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `food_types` json COMMENT 'food_type.type arr',
  `region_value` varchar(255) NOT NULL,
  `addr1` varchar(255) NOT NULL,
  `addr2` varchar(255) NOT NULL,
  `addr_description` varchar(255) COMMENT '찾아오는 법',
  `addr_description_image_ids` json COMMENT '찾아오는 법 이미지 배열',
  `contact` varchar(255),
  `homepage` varchar(255),
  `image_ids` json COMMENT '이미지 id array',
  `tables_usage_hour` int NOT NULL,
  `refund_policy` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `region` (
  `value` varchar(255) PRIMARY KEY,
  `parent` varchar(255)
);

CREATE TABLE `restaurant_reservation_schedule` (
  `id` bigint PRIMARY KEY,
  `restaurant_id` bigint NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `menu_category_ids` json NOT NULL COMMENT 'menu_category.id array'
);

CREATE TABLE `restaurant_coupon` (
  `id` bigint PRIMARY KEY,
  `restaurant_id` bigint,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type` ENUM ('FIXED_VALUE', 'FIXED_RATE') NOT NULL,
  `value` int NOT NULL COMMENT '할인 금액 또는 할인 율',
  `require_minimum_price` int,
  `max_discount_price` int,
  `use_manual` varchar(255) NOT NULL COMMENT '사용 방법',
  `use_term` varchar(255) NOT NULL COMMENT '사용 조건',
  `start_date_time` varchar(255) NOT NULL,
  `end_date_time` varchar(255) NOT NULL
);

CREATE TABLE `restaurant_amenity` (
  `restaurant_id` bigint NOT NULL,
  `amenity_id` int NOT NULL
);

CREATE TABLE `restaurant_amenity_info` (
  `restaurant_id` bigint NOT NULL,
  `amenity_id` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `price_info` varchar(255),
  `check_info` varchar(255)
);

CREATE TABLE `amenity` (
  `id` int PRIMARY KEY,
  `type` varchar(255) NOT NULL
);

CREATE TABLE `restaurant_announcement` (
  `id` bigint PRIMARY KEY,
  `restaurant_id` bigint NOT NULL,
  `type` ENUM ('ANNOUNCEMENT', 'PROMOTION', 'EVENT') NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `menu_category` (
  `id` bigint PRIMARY KEY,
  `restaurant_id` bigint NOT NULL,
  `order` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `menu` (
  `id` bigint PRIMARY KEY,
  `restaurant_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  `types` json COMMENT 'menu_type.type arr',
  `name` varchar(255) NOT NULL,
  `description` varchar(255),
  `price` int NOT NULL,
  `order` int NOT NULL,
  `image_ids` json COMMENT 'image.id array',
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `menu_type` (
  `type` varchar(255) PRIMARY KEY
);

CREATE TABLE `tables` (
  `id` bigint PRIMARY KEY,
  `restaurant_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` ENUM ('HALL', 'ROOM', 'TERRACE') NOT NULL,
  `minimum_personnel` tinyint NOT NULL,
  `maximum_personnel` tinyint NOT NULL
);

CREATE TABLE `manager` (
  `id` bigint PRIMARY KEY,
  `email` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL
);

CREATE TABLE `restaurant_manager` (
  `restaurant_id` bigint NOT NULL,
  `manager_id` bigint NOT NULL,
  `authorization` json COMMENT 'manager_authority_type arr'
);

CREATE TABLE `reservation` (
  `id` bigint PRIMARY KEY,
  `user_id` bigint NOT NULL,
  `restaurant_id` bigint NOT NULL,
  `coupon_ids` json,
  `party` json NOT NULL COMMENT 'child: int, adult: int, disabled: int',
  `time` datetime NOT NULL DEFAULT (now()),
  `note` varchar(255),
  `tables_ids` json NOT NULL COMMENT 'tables.id array',
  `reserve_purpose_type` ENUM ('DATE', 'FRIENDSHIP', 'FAMILY', 'BIRDAY', 'ANNIVERSARY', 'TRAVEL', 'BUSINESS', 'BLIND_DATE', 'ETC') NOT NULL DEFAULT 'ETC',
  `payment` json NOT NULL COMMENT 'date: datetime, price: int, type1: varchar, type2: varchar',
  `status` ENUM ('REQUEST', 'CONFIRM', 'VISIT', 'NO_SHOW', 'CANCEL') NOT NULL DEFAULT 'REQUEST',
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now())
);

CREATE TABLE `reservation_menu` (
  `id` bigint,
  `menu_id` bigint,
  `count` int NOT NULL DEFAULT 1,
  `note` varchar(255),
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id`, `menu_id`)
);

CREATE TABLE `reservation_accompany` (
  `id` bigint,
  `accompany_id` bigint,
  `created_at` datetime NOT NULL DEFAULT (now()),
  `modified_at` datetime NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id`, `accompany_id`)
);

CREATE TABLE `image` (
  `id` bigint PRIMARY KEY,
  `src` varchar(255) NOT NULL COMMENT 'Image URL',
  `created_at` datetime NOT NULL DEFAULT (now())
);

CREATE UNIQUE INDEX `user_terms_index_0` ON `user_terms` (`user_id`, `term_id`);

CREATE UNIQUE INDEX `user_follower_index_1` ON `user_follower` (`user_id`, `follower_id`);

CREATE UNIQUE INDEX `user_anniversary_index_2` ON `user_anniversary` (`user_id`, `type`);

CREATE UNIQUE INDEX `user_service_notification_index_3` ON `user_service_notification` (`user_id`, `type`);

CREATE UNIQUE INDEX `user_bookmark_index_4` ON `user_bookmark` (`user_id`, `restaurant_id`);

CREATE UNIQUE INDEX `user_restaurant_coupon_index_5` ON `user_restaurant_coupon` (`user_id`, `coupon_id`);

CREATE UNIQUE INDEX `collection_restaurant_index_6` ON `collection_restaurant` (`collection_id`, `restaurant_id`);

CREATE UNIQUE INDEX `user_advertisement_notification_index_7` ON `user_advertisement_notification` (`user_id`, `type`);

CREATE UNIQUE INDEX `user_like_index_8` ON `user_like` (`user_id`, `object_id`);

CREATE UNIQUE INDEX `restaurant_amenity_info_index_9` ON `restaurant_amenity_info` (`restaurant_id`, `amenity_id`);

CREATE UNIQUE INDEX `menu_category_index_10` ON `menu_category` (`restaurant_id`, `order`);

CREATE UNIQUE INDEX `menu_index_11` ON `menu` (`restaurant_id`, `category_id`, `order`);

CREATE UNIQUE INDEX `tables_index_12` ON `tables` (`restaurant_id`, `type`, `name`);

CREATE UNIQUE INDEX `reservation_menu_index_13` ON `reservation_menu` (`id`, `menu_id`);

CREATE UNIQUE INDEX `reservation_accompany_index_14` ON `reservation_accompany` (`id`, `accompany_id`);

CREATE UNIQUE INDEX `restaurant_amenity_index_15` ON `restaurant_amenity` (`restaurant_id`, `amenity_id`);

ALTER TABLE `reservation_accompany` COMMENT = '예약 함께 방문';

ALTER TABLE `user_terms` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_terms` ADD FOREIGN KEY (`term_id`) REFERENCES `terms` (`id`);

ALTER TABLE `user` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`id`);

ALTER TABLE `user_sns` ADD FOREIGN KEY (`id`) REFERENCES `user` (`id`);

ALTER TABLE `user_block` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_follower` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_follower` ADD FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_preference_price` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_anniversary` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_preference_food` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_service_notification` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_bookmark` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_bookmark` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `user_restaurant_coupon` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_restaurant_coupon` ADD FOREIGN KEY (`coupon_id`) REFERENCES `restaurant_coupon` (`id`);

ALTER TABLE `collection` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `collection_restaurant` ADD FOREIGN KEY (`collection_id`) REFERENCES `collection` (`id`);

ALTER TABLE `collection_restaurant` ADD FOREIGN KEY (`user_id`, `restaurant_id`) REFERENCES `user_bookmark` (`user_id`, `restaurant_id`);

ALTER TABLE `user_advertisement_notification` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`review_id`) REFERENCES `review` (`id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`);

ALTER TABLE `user_like` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_like` ADD FOREIGN KEY (`object_id`) REFERENCES `comment` (`id`);

ALTER TABLE `user_like` ADD FOREIGN KEY (`object_id`) REFERENCES `review` (`id`);

ALTER TABLE `review` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `review` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `restaurant` ADD FOREIGN KEY (`admin_id`) REFERENCES `manager` (`id`);

ALTER TABLE `restaurant` ADD FOREIGN KEY (`region_value`) REFERENCES `region` (`value`);

ALTER TABLE `region` ADD FOREIGN KEY (`parent`) REFERENCES `region` (`value`);

ALTER TABLE `restaurant_reservation_schedule` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `restaurant_coupon` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `restaurant_amenity` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `restaurant_amenity` ADD FOREIGN KEY (`amenity_id`) REFERENCES `amenity` (`id`);

ALTER TABLE `restaurant_amenity_info` ADD FOREIGN KEY (`restaurant_id`, `amenity_id`) REFERENCES `restaurant_amenity` (`restaurant_id`, `amenity_id`);

ALTER TABLE `restaurant_announcement` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `menu_category` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `menu` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `menu` ADD FOREIGN KEY (`category_id`) REFERENCES `menu_category` (`id`);

ALTER TABLE `tables` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `restaurant_manager` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `restaurant_manager` ADD FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`);

ALTER TABLE `reservation` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `reservation` ADD FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`);

ALTER TABLE `reservation_menu` ADD FOREIGN KEY (`id`) REFERENCES `reservation` (`id`);

ALTER TABLE `reservation_menu` ADD FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`);

ALTER TABLE `reservation_accompany` ADD FOREIGN KEY (`id`) REFERENCES `reservation` (`id`);

ALTER TABLE `reservation_accompany` ADD FOREIGN KEY (`accompany_id`) REFERENCES `user` (`id`);

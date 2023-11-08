create table PRODUCT_PRICE
(
    id          bigint auto_increment
        primary key,
    brand       varchar(255) not null,
    category    varchar(255) not null,
    price    bigint,
    date_created datetime(3)  null,
    date_updated datetime(3)  null
);

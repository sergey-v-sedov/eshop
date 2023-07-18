CREATE TABLE IF NOT EXISTS CART(ID uuid DEFAULT gen_random_uuid() PRIMARY KEY, PROFILE_ID uuid UNIQUE NOT NULL);
CREATE TABLE IF NOT EXISTS CART_ITEM(ID uuid DEFAULT gen_random_uuid() PRIMARY KEY, CART_ID uuid NOT NULL, PRODUCT_ID uuid NOT NULL, CONSTRAINT product_in_cart_unique_constraint UNIQUE (CART_ID, PRODUCT_ID));
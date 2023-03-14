drop database smack;

create database smack;

use smack;

create table listings (id int, name varchar(250), brand varchar(100), price float, category varchar(50), description text, image varchar(250), quantity int, PRIMARY KEY(id));
create table cart (id int NOT NULL AUTO_INCREMENT, user_id int, listing_id int, quantity int, PRIMARY KEY(id));
create table users (id int, username varchar(100), password varchar(100), PRIMARY KEY(id));


insert into listings values (0,"5th Season - Crunchy Organic Banana & Blueberry Bites","5th Season",4.40,"dried fruit","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Blueberrypackshot_400x.jpg?v=1651191339",41);
insert into listings values (1,"5th Season - Heavenly Pineapple Bites","5th Season",3.90,"dried fruit","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/5thSeason-Pineapple_400x.jpg?v=1647323515",12);
insert into listings values (2,"5th Season - Marvellous Organic Mango and Raspberries Bites","5th Season",4.40,"dried fruit","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/5thSeason-Organicmangoandstrawberry_400x.jpg?v=1647323525",15);
insert into listings values (3,"5th Season - Scrumptious Strawberry Bites","5th Season",3.90,"dried fruit","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/5thSeason-Strawberries_400x.jpg?v=1647323532",12);
insert into listings values (4,"Arva Premium Tempe Chips - Black Sesame","Arva",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Arva-BlackSesame_Front_400x.png?v=1666656880",16);
insert into listings values (5,"Arva Premium Tempe Chips - Himalayan Salt Basil","Arva",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Arva_Himalayansaltbasil_FRONT_400x.jpg?v=1647323543",32);
insert into listings values (6,"Arva Premium Tempe Chips - Honey Butter","Arva",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Arva_HoneyButter_FRONT_400x.jpg?v=1647323556",6);
insert into listings values (7,"Arva Premium Tempe Chips - Mala","Arva",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Arva_Mala_FRONT_400x.jpg?v=1647323571",19);
insert into listings values (8,"Arva Premium Tempe Chips - Truffle","Arva",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Arva_Truffle_FRONT_400x.jpg?v=1647323597",10);
insert into listings values (9,"Boundless - Chipotle & Lime Chips","Boundless",3.90,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Boundless_Chipotle_Lime_80g_FRONT_400x.jpg?v=1647323701",36);
insert into listings values (10,"Boundless - Sea Salt & Cider Vinegar","Boundless",3.90,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Boundless_Seasalt_80g_FRONT_400x.jpg?v=1647323719",18);
insert into listings values (11,"Capital Crisps - Bangkok Hot Chilli","Capital",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Capital-Crisps--Hot-Chilli_400x.jpg?v=1660349695",14);
insert into listings values (12,"Capital Crisps - Berlin Sour Cream & Onion","Capital",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Capital-Crisps--Sour-Cream-_-Onion_400x.jpg?v=1660349696",19);
insert into listings values (13,"Capital Crisps - Edinburgh Sea Salt","Capital",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Capital-Crisps--Sea-Salt_400x.jpg?v=1660349697",40);
insert into listings values (14,"Capital Crisps - London Salt & Vinegar","Capital",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Capital-Crisps--S_V_400x.jpg?v=1660349698",40);
insert into listings values (15,"Capital Crisps - Madrid Smoky Paprika","Capital",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Capital-Crisps--Smoky-Paprika_400x.jpg?v=1660349699",27);
insert into listings values (16,"Capital Crisps - Paris Tangy Cheese","Capital",6.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Capital-Crisps--Tangy-Cheese_400x.jpg?v=1660349699",9);
insert into listings values (17,"Chip Chip Hooray Premium Taro Snack - Roast Beef","Chip Chip Hooray",5.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/CCH-RoastBeef_Front_400x.jpg?v=1658362502",13);
insert into listings values (18,"Chip Chip Hooray Premium Taro Snack - Sambal Jeruk","Chip Chip Hooray",5.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/CCH-SambalJeruk_Front_400x.jpg?v=1658362499",33);
insert into listings values (19,"Chip Chip Hooray Premium Taro Snack - Savoury Original","Chip Chip Hooray",5.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/CCH-Original_Front_400x.jpg?v=1658362500",44);
insert into listings values (20,"Chip Chip Hooray Premium Taro Snack - Sour Cream & Onion","Chip Chip Hooray",5.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/CCH-SourCream_Front_400x.jpg?v=1658362497",42);
insert into listings values (21,"Chip-Sticks","",5.90,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/CT-product-images_Chipsticks_400x.jpg?v=1647324032",30);
insert into listings values (22,"Good & Honest Popped Crisps - Salted","Good & Honest",4.00,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Good___Honest_Popped_Sea_Salt_23g_400x.jpg?v=1660263313",15);
insert into listings values (23,"Good & Honest Popped Crisps - Sweet BBQ","Good & Honest",6.90,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Good_Honest_Sweet_BBQ_Popped_Crisps_400x.jpg?v=1660263316",35);
insert into listings values (24,"Good & Honest Popped Veggie Crisps - Sour Cream","Good & Honest",7.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Good_HonestPoppedVeggieSoya_PeaSourCream_85g_400x.jpg?v=1660263318",24);
insert into listings values (25,"Good & Honest Popped Veggie Crisps - Sweet Chilli","Good & Honest",4.00,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Good___Honest_Popped_Veggie_Sweet_Potato___Pea_Sweet_Chilli_23g_400x.jpg?v=1660263319",17);
insert into listings values (26,"Karma Bites Popped Lotus Seeds - Caramel","Karma Bites",4.50,"seeds","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/KarmaBites_Caramel_FRONT_400x.jpg?v=1647324656",25);
insert into listings values (27,"Karma Bites Popped Lotus Seeds - Peri Peri","Karma Bites",4.50,"seeds","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/KarmaBites_PeriPeri_FRONT_400x.jpg?v=1647324721",49);
insert into listings values (28,"Karma Bites Popped Lotus Seeds - Wasabi","Karma Bites",4.50,"seeds","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/KarmaBites_Wasabi_FRONT_400x.jpg?v=1647324738",36);
insert into listings values (29,"Kent Crisps - Ashmore Cheese & Onion","Kent Crisps",4.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/KentCrisps_AshmoreCheese_Onion_30g_400x.jpg?v=1647324793",9);
insert into listings values (30,"Kent Crisps - Ham & Mustard","Kent Crisps",4.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/KentCrisps_Ham_Mustard_40g_400x.jpg?v=1647324809",11);
insert into listings values (31,"Kent Crisps - Sea Salt","Kent Crisps",4.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/KentCrisps_SeaSalt_40g_400x.jpg?v=1647324838",20);
insert into listings values (32,"Kent Crisps - Smoked Chipotle Chilli","Kent Crisps",4.50,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/KentCrisps_SmokedChipotle_40g_400x.jpg?v=1647324869",44);
insert into listings values (33,"Maimai Thai Handmade Banana Chips - Caramel Flavoured","Maimai Thai",5.20,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/MaiMaiCaramel_FRONT_400x.jpg?v=1647324915",7);
insert into listings values (34,"Maimai Thai Handmade Banana Chips - Paprika Flavoured","Maimai Thai",5.20,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/MaiMaiPaprika_FRONT_400x.jpg?v=1647324928",35);
insert into listings values (35,"Oh Ma Grain! Popped Rice Crackers - Milky Chocolate","Oh Ma Grain!",5.50,"crackers","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/OhMaGrain-MilkyChocolate_Front_400x.png?v=1666656883",19);
insert into listings values (36,"Oh Ma Grain! Popped Rice Crackers - Salt & Black Pepper","Oh Ma Grain!",5.20,"crackers","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/OhMaGrain-SaltandBlackPepper_Front_400x.png?v=1666656887",49);
insert into listings values (37,"Oh Ma Grain! Popped Rice Crackers - Salted Caramel","Oh Ma Grain!",5.50,"crackers","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/OhMaGrain-SaltedCaramel_Front_400x.png?v=1666656886",48);
insert into listings values (38,"Oh Ma Grain! Popped Rice Crackers - Seaweed","Oh Ma Grain!",5.20,"crackers","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/OhMaGrain-Seaweed_Front_400x.png?v=1666656882",14);
insert into listings values (39,"Pea Pops - Cheddar & Onion Chickpea Crisps","Pea Pops",4.20,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Pea-Pops_Cheddar-Onion_80g_400x.jpg?v=1660349704",39);
insert into listings values (40,"Pea Pops - Chilli & Lime Chickpea Crisps","Pea Pops",4.20,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Pea-Pops_Chilli-Lime_80g_400x.jpg?v=1660349705",13);
insert into listings values (41,"Pea Pops - Smoky BBQ Chickpea Crisps","Pea Pops",4.20,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Pea-Pops_Smoky-BBQ_80g_400x.jpg?v=1660349706",30);
insert into listings values (42,"Pretzel Pete - Buffalo Blue","Pretzel Pete",8.50,"pretzel","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Pretzel-Pete--Buffalo-Blue_400x.jpg?v=1660349700",40);
insert into listings values (43,"Pretzel Pete - Cinnamon Brown Sugar","Pretzel Pete",8.50,"pretzel","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Pretzel-Pete--Cinnamon-Brown-Sugar_400x.jpg?v=1660349701",45);
insert into listings values (44,"Pretzel Pete - Honey Mustard & Onion","Pretzel Pete",8.50,"pretzel","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Pretzel-Pete--Honey-Mustard-_-Onion_400x.jpg?v=1660349702",16);
insert into listings values (45,"Proppadoms by Family Secret - Jalapeno","Family Secret",3.90,"chips","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Proppadom-Jalapeno_Front_400x.jpg?v=1647325117",49);
insert into listings values (46,"Well & Truly Crunchies - Really Cheesy","Well & Truly",3.90,"crunchies","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Well_Truly_Reallycheesy_30g_FRONT_400x.jpg?v=1647325543",27);
insert into listings values (47,"Well & Truly Crunchies - Smokey Paprika","Well & Truly",3.90,"crunchies","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Well_Truly_SmokeyPaprika_30g_FRONT_400x.jpg?v=1647325576",35);
insert into listings values (48,"Well & Truly Crunchies - Sour Cream & Onion","Well & Truly",3.90,"crunchies","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/Well_Truly_Sourcream_onion_30g_FRONT_400x.jpg?v=1647325589",31);
insert into listings values (49,"Well & Truly Crunchies - Spicey & Nicey","Well & Truly",3.90,"crunchies","","//cdn.shopify.com/s/files/1/0586/4328/4126/products/spiceyandniceywellandtruly_400x.jpg?v=1670976907",9);

insert into users values (0, "myuser1", "myuser1");
insert into users values (1, "myuser2", "myuser2");
insert into users values (3, " ", " ");


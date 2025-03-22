select * from product_discount;

select pm.has_discount
from product_main pm
where pm.has_discount = true;

select *
from product_main;

select *
from product_discount;

drop table product_discount;
drop table product_main;

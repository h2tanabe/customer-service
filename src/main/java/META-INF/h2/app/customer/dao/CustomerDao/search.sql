select
  *
from
  customers
where
1 = 1

/*%if @isNotBlank(customer.name) */
and
name like /*  @prefix(customer.name) */'a%'
/*%end*/

/*%if @isNotBlank(customer.address) */
and
address like /* @prefix(customer.address) */'b%'
/*%end*/

/*%if @isNotBlank(customer.tel) */
and
tel = /* customer.tel */'dd'
/*%end*/

/*%if @isNotBlank(customer.mail) */
and
mail = /* customer.mail */'dd'
/*%end*/

/*# orderBy */


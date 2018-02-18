select
    a.*
  , b.name as customer_name
  , b.postal_code
  , b.address
from
  opportunities as a
inner join
  customers as b on a.account_id = b.id
/*# orderBy */

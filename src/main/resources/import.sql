insert into cozinha (nome) values('Tailandesa');
insert into cozinha (nome) values('Indiana');


insert into forma_pagamento (descricao) values('Cartão de crédito');
insert into forma_pagamento (descricao) values('Cartão de débito');

insert into restaurante (nome, taxa_frete, cozinha_id) values('Ponto do caranguejo', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values('Bar do Chico', 10, 2);

insert into restaurante_pagamento (restaurante_id, pagamento_id) values(1, 1); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(2, 1); 
insert into restaurante_pagamento (restaurante_id, pagamento_id) values(2, 2); 


insert into estado (nome) values('Ceará');

insert into cidade (nome, estado_id) values('Fortaleza', 1);
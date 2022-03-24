
INSERT INTO coches (marca,modelo) VALUES ('Volswagen','Golf');
INSERT INTO coches (marca,modelo) VALUES ('Audi','A3');
INSERT INTO coches (marca,modelo) VALUES ('Renault','Kangoo');
INSERT INTO coches (marca,modelo) VALUES ('Seat','Ibiza');
INSERT INTO coches (marca,modelo) VALUES ('Mercedes','A200');
INSERT INTO coches (marca,modelo) VALUES ('Honda','Civic');
INSERT INTO coches (marca,modelo) VALUES ('Citroen','Picasso');
INSERT INTO coches (marca,modelo) VALUES ('Toyota','Celica');

INSERT INTO usuarios (username,password,enabled) VALUES('rolando','$2a$10$fmWoPIGU9v/URlUFpuOlWuFft5aeSpAOCerJuE342WGgLDzbocrp',1);
INSERT INTO usuarios (username,password,enabled) VALUES('admin','$2a$10$Er7QCutA6/ZBS9tO5M1F9.Ah1sNMigVK0Sm.Scy35jWHib7O7ra1q',1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);


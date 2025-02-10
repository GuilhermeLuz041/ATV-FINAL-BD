CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    anoPublicacao INT NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    disponivel BOOLEAN NOT NULL
);


CREATE TABLE membro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco TEXT NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT NOT NULL,
    membro_id INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_prevista_devolucao DATE NOT NULL,
    data_devolucao DATE,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (membro_id) REFERENCES membro(id)
);

CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT NOT NULL,
    membro_id INT NOT NULL,
    data_reserva DATE NOT NULL,
    FOREIGN KEY (livro_id) REFERENCES livro(id),
    FOREIGN KEY (membro_id) REFERENCES membro(id)
);
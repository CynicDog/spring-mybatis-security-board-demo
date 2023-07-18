CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
                       user_id INT,
                       role_name VARCHAR(128) NOT NULL,
                       CONSTRAINT composite_pk PRIMARY KEY (user_id, role_name),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE boards (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        title VARCHAR(255) NOT NULL,
                        content VARCHAR(4000) NOT NULL,
                        read_count INT DEFAULT 0,
                        review_count FLOAT DEFAULT 0,
                        review_avg_score FLOAT DEFAULT 0.0,
                        deleted CHAR(1) DEFAULT 'N',
                        updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        user_id INT,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE comments (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          content VARCHAR(4000) NOT NULL,
                          user_id INT,
                          board_id INT,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (board_id) REFERENCES boards(id)
);

CREATE TABLE IF NOT EXISTS tb_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tb_account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    balance DECIMAL DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS tb_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(120) NOT NULL,
    password VARCHAR(120) NOT NULL,
    role_id INT NOT NULL,
    account_id INT NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (role_id) REFERENCES tb_role(id),
    FOREIGN KEY (account_id) REFERENCES tb_account(id)
);

CREATE TABLE IF NOT EXISTS tb_financial_active (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    date_issue DATE NOT NULL,
    date_terminus DATE NOT NULL,
    active BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS tb_financial_movement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    value_overall DECIMAL(10, 8) NOT NULL,
    is_entry BOOLEAN,
    financial_active_id INT NOT NULL,
    date_movement DATE NOT NULL,
    account_id INT NOT NULL,
    FOREIGN KEY (financial_active_id) REFERENCES tb_financial_active(id),
    FOREIGN KEY (account_id) REFERENCES tb_account(id)
);

CREATE TABLE IF NOT EXISTS tb_financial_release (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    value_release DECIMAL(10, 8) NOT NULL,
    date_movement DATE NOT NULL,
    is_entry BOOLEAN,
    account_id INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES tb_account(id)
);

CREATE TABLE IF NOT EXISTS tb_position (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_active VARCHAR(255) NOT NULL UNIQUE,
    type_active VARCHAR(50) NOT NULL,
    amount_available DECIMAL NOT NULL,
    value_marketplace DECIMAL NOT NULL,
    value_yield DECIMAL DEFAULT 0.0,
    value_gain DECIMAL DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS users
(
  id                    VARCHAR(36) PRIMARY KEY,
  email                 VARCHAR(255) NOT NULL UNIQUE,
  password_hash         VARCHAR(255) NOT NULL,
  created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  is_active             BOOLEAN   DEFAULT true,
  first_name            VARCHAR(127) NOT NULL,
  last_name             VARCHAR(127) NOT NULL,
  last_login            TIMESTAMP,
  failed_login_attempts INT       DEFAULT 0,
  verification_token    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS roles
(
  id        BIGINT auto_increment PRIMARY KEY,
  role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles
(
  user_id VARCHAR(36) NOT NULL,
  role_id BIGINT      NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS sessions
(
  id         VARCHAR(36) PRIMARY KEY,
  user_id    VARCHAR(36) NOT NULL,
  jwt_token  TEXT        NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  expires_at TIMESTAMP   NOT NULL,
  is_revoked BOOLEAN   DEFAULT false,
  FOREIGN KEY (user_id) REFERENCES users (id)
);
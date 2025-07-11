
CREATE INDEX idx_users_name_pattern
    ON users (first_name text_pattern_ops, last_name text_pattern_ops);
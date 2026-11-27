INSERT INTO players (
    player_name,
    email,
    password,
    created_at,
    updated_at
) VALUES (
    '太郎',
    'test@example.com',
    -- "password123"をbcryptでハッシュ化されたパスワード
    '$2a$10$lez5ugMRmh.KT4Iaza7pIOfZVrQoQxCeLbhqWUZBkZMHU44xHJ7rm',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
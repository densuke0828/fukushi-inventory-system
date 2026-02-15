-- 初期データ: メーカーデータ
INSERT INTO manufactures (name, created_at, updated_at) VALUES
('パラマウントベッド', NOW(), NOW()),
('シーホネンス', NOW(), NOW()),
('ランダル', NOW(), NOW()),
('フランスベッド', NOW(), NOW()),
('プラッツ', NOW(), NOW()),
('矢崎化工', NOW(), NOW()),
('ホクメイ', NOW(), NOW()),
('パナソニック', NOW(), NOW()),
('マツ六', NOW(), NOW()),
('星光医療', NOW(), NOW()),
('幸和製作所', NOW(), NOW()),
('島製作所', NOW(), NOW()),
('竹虎', NOW(), NOW()),
('ミキ', NOW(), NOW()),
('カワムラサイクル', NOW(), NOW()),
('日進医療器', NOW(), NOW()),
('松永製作所', NOW(), NOW()),
('ケープ', NOW(), NOW()),
('タイカ', NOW(), NOW()),
('スズキ', NOW(), NOW()),
('セリオ', NOW(), NOW()),
('WHILL', NOW(), NOW());

-- 初期データ: 用具種目データ
INSERT INTO categories (name, created_at, updated_at) VALUES
('特殊寝台', NOW(), NOW()),
('特殊寝台付属品', NOW(), NOW()),
('床ずれ防止用具', NOW(), NOW()),
('体位変換器', NOW(), NOW()),
('手すり', NOW(), NOW()),
('スロープ', NOW(), NOW()),
('車いす', NOW(), NOW()),
('車いす付属品', NOW(), NOW()),
('歩行器', NOW(), NOW()),
('歩行補助杖', NOW(), NOW()),
('移動用リフト', NOW(), NOW()),
('徘徊感知機器', NOW(), NOW()),
('自動排泄処理装置', NOW(), NOW());

-- 初期データ: 保管場所データ
INSERT INTO locations (name, created_at, updated_at) VALUES
('本社', NOW(), NOW()),
('第一倉庫', NOW(), NOW()),
('第二倉庫', NOW(), NOW()),
('利用者宅', NOW(), NOW());

-- 初期データ: 用具状態データ
INSERT INTO statuses (status, display_name, created_at, updated_at) VALUES
('AVAILABLE', '在庫', NOW(), NOW()),
('RENTED', '貸出中', NOW(), NOW()),
('CLEANING', '清掃中', NOW(), NOW()),
('REPAIR', '修理中', NOW(), NOW());

-- 初期データ: 製品データ
INSERT INTO products (
    name,
    tais_code,
    categories_id,
    manufactures_id,
    created_at,
    updated_at
) VALUES (
    'ハッピーⅡ NB',
    '00110-000112',
    (SELECT id FROM categories WHERE name = '歩行器'),
    (SELECT id FROM manufactures WHERE name = '竹虎'),
    NOW(),
    NOW()
), (
    '楽匠Z 3モーター',
    '00170-000785',
    (SELECT id FROM categories WHERE name = '特殊寝台'),
    (SELECT id FROM manufactures WHERE name = 'パラマウントベッド'),
    NOW(),
    NOW()
), (
    'ベスポジZ レギュラー',
    '00254-000410',
    (SELECT id FROM categories WHERE name = '手すり'),
    (SELECT id FROM manufactures WHERE name = 'ホクメイ'),
    NOW(),
    NOW()
);

-- 初期データ: 在庫データ
INSERT INTO stocks (
    serial_code,
    products_id,
    locations_id,
    statuses_id,
    purchased_at,
    notes,
    created_at,
    updated_at
) VALUES (
    'WK-0001',
    (SELECT id FROM products WHERE name = 'ハッピーⅡ NB'),
    (SELECT id FROM locations WHERE name = '本社'),
    (SELECT id FROM statuses WHERE status = 'AVAILABLE'),
    '2025-02-02',
    '新品未開封',
    NOW(),
    NOW()
), (
    'BD-0001',
    (SELECT id FROM products WHERE name = '楽匠Z 3モーター'),
    (SELECT id FROM locations WHERE name = '本社'),
    (SELECT id FROM statuses WHERE status = 'AVAILABLE'),
    '2023-11-02',
    '',
    NOW(),
    NOW()
), (
    'HR-0001',
    (SELECT id FROM products WHERE name = 'ベスポジZ レギュラー'),
    (SELECT id FROM locations WHERE name = '第一倉庫'),
    (SELECT id FROM statuses WHERE status = 'REPAIR'),
    '2025-02-02',
    'バネ発注中',
    NOW(),
    NOW()
);


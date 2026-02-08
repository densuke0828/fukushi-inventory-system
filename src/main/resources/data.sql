-- 初期データ: メーカーデータ
INSERT INTO manufactures (name, created_at, updated_at) VALUES
('パラマウント', NOW(), NOW()),
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
('片江', NOW(), NOW()),
('長尾', NOW(), NOW());

-- 初期データ: 用具状態データ
INSERT INTO statuses (status, display_name, created_at, updated_at) VALUES
('AVAILABLE', '在庫', NOW(), NOW()),
('RENTED', '貸出中', NOW(), NOW()),
('CLEANING', '清掃中', NOW(), NOW()),
('REPAIR', '修理中', NOW(), NOW());

-- 初期データ: 用具データ(保留)


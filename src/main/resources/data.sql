-- 初期データ: 福祉用具
INSERT INTO equipment (equipment_code, name, category, status, description, created_at, updated_at) VALUES
('WC-001', '自走式車椅子 A', 'WHEELCHAIR', 'AVAILABLE', '標準型自走式車椅子', NOW(), NOW()),
('WC-002', '電動車椅子 B', 'WHEELCHAIR', 'RENTED', '電動アシスト付き車椅子', NOW(), NOW()),
('WC-003', '介助式車椅子 C', 'WHEELCHAIR', 'AVAILABLE', 'コンパクト折りたたみ式', NOW(), NOW()),
('SB-001', '特殊寝台 A', 'SPECIAL_BED', 'AVAILABLE', '3モーター電動ベッド', NOW(), NOW()),
('SB-002', '特殊寝台 B', 'SPECIAL_BED', 'CLEANING', '2モーター電動ベッド', NOW(), NOW()),
('WK-001', '歩行器 A', 'WALKER', 'AVAILABLE', '固定型歩行器', NOW(), NOW()),
('WK-002', '歩行器 B', 'WALKER', 'RENTED', 'キャスター付き歩行器', NOW(), NOW()),
('HR-001', '手すり A', 'HANDRAIL', 'AVAILABLE', '据え置き型手すり', NOW(), NOW()),
('SL-001', 'スロープ A', 'SLOPE', 'AVAILABLE', '段差解消スロープ 長さ2m', NOW(), NOW()),
('PM-001', '床ずれ防止マット A', 'PRESSURE_MATTRESS', 'AVAILABLE', 'エアマットレス', NOW(), NOW())
ON CONFLICT DO NOTHING;

-- 初期データ: 貸出履歴
INSERT INTO rental_record (equipment_id, renter_name, renter_contact, rental_date, expected_return_date, actual_return_date, notes, created_at, updated_at) VALUES
(2, '山田 太郎', '090-1234-5678', '2026-01-15', '2026-04-15', NULL, '介護保険適用', NOW(), NOW()),
(7, '佐藤 花子', '090-8765-4321', '2026-01-20', '2026-03-20', NULL, '', NOW(), NOW())
ON CONFLICT DO NOTHING;

# Fukushi Inventory System - 福祉用具在庫管理システム

## アプリケーションの概要

**福祉用具(車椅子・特殊寝台・歩行器など) の在庫管理を行う Web アプリケーション**

## アプリケーション変更履歴

- [変更履歴はこちら (CHANGELOG.md)](./CHANGELOG.md)

## 開発状況

現在の詳細な進捗状況やタスクの優先順位は、以下のProjectボードで管理してしています
- [開発ロードマップ (GitHub Projects)](https://github.com/users/densuke0828/projects/1)

## 技術スタック

| レイヤー        | 技術                                                                                                                   |
|:------------|:---------------------------------------------------------------------------------------------------------------------|
| 言語          | ![Java](https://img.shields.io/badge/Java-21-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)             |
| フレームワーク     | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) |
| ORM         | ![MyBatis](https://img.shields.io/badge/MyBatis-C1122D?style=for-the-badge&logo=swift&logoColor=white)　              |
| データベース      | ![PostgreSQL](https://img.shields.io/badge/Postgres-316192?style=for-the-badge&logo=postgresql&logoColor=white)      |
| テンプレートエンジン  | ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)       |
| バリデーション     | Jakarta Validation                                                                                                   |
| ビルドツール      | ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)                |
| コード生成       | ![Lombok](https://img.shields.io/badge/Lombok-BC2139?style=for-the-badge&logo=lombok&logoColor=white)                |
| テスト         | JUnit 5                                                                                                              |

## ディレクトリ構成

```
src/main/java/com/example/fukushi/
├── FukushiInventoryApplication.java   # Spring Boot エントリーポイント
├── controller/                        # HTTP リクエストの受付・画面遷移制御
│   ├── HomeController.java
│   └── StockController.java
├── service/                           # ビジネスロジック・トランザクション管理
│   └── StockService.java
├── repository/                        # データアクセス層 (Spring Data JPA)
│   └── StockRepository.java
├── entity/                            # JPA エンティティ (DB テーブルマッピング)
│   ├── Category.java
│   ├── Location.java
│   ├── Manufacture.java
│   ├── Product.java
│   ├── Status.java
│   └── Stock.java
├── dto/                               # データ転送オブジェクト (入力バリデーション)
│   └── StockDto.java
├── enums/                             # 列挙型 (カテゴリ・ステータス定義)
│   └── EquipmentCategory.java
└── config/                            # 設定クラス (拡張用)
    ├── JpaConfig.java     
    └── ThymeleafConfig.java                      

src/main/resources/
├── application.yml                    # アプリケーション設定
├── data.sql                           # 初期データ投入
├── schema.sql                         # DBスキーマ定義
├── static/css/style.css               # スタイルシート
└── templates/                         # Thymeleaf テンプレート
    ├── index.html                     #   ダッシュボード
    ├── fragments/layout.html          #   共通レイアウト
    ├── equipment/list.html            #   用具一覧
    ├── equipment/form.html            #   用具登録・編集フォーム
    ├── rental/list.html               #   貸出一覧
    └── rental/form.html               #   貸出登録フォーム
```

## DB テーブル

```
-- メーカーテーブル (manufactures)
CREATE TABLE manufactures (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 用具種目テーブル (categories)
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 保管場所テーブル (locations)
CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 用具状態テーブル (statuses)
CREATE TABLE statuses (
    id SERIAL PRIMARY KEY,
    status VARCHAR(20) UNIQUE NOT NULL,
    display_name VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 製品テーブル (products)
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    categories_id INT REFERENCES categories(id),
    manufactures_id INT REFERENCES manufactures(id),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 保有商品テーブル (stocks)
CREATE TABLE stocks (
    id SERIAL PRIMARY KEY,
    serial_code VARCHAR(30) UNIQUE NOT NULL,
    products_id INT NOT NULL REFERENCES products(id),
    locations_id INT NOT NULL REFERENCES locations(id),
    statuses_id INT NOT NULL REFERENCES statuses(id),
    purchased_at DATE,
    notes VARCHAR(500),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

## 主要クラスの説明

### Controller 層

- **HomeController** — ダッシュボード画面を提供。用具の総数・ステータス別件数などを集計して表示する
- **StockController** — 用具の CRUD 操作を担当。一覧表示（カテゴリ・ステータスでのフィルタリング対応）、新規登録・編集フォーム表示、保存、削除のエンドポイントを持つ

### Service 層

- **StockService** — Controller から用具の検索・保存・削除・ステータス更新を受け付ける。また、Entity → DTO 変換メソッドの呼び出しを記述する

### Repository 層

- **StockRepository** — `JpaRepository<hoge, huga>` を継承。管理番号による検索、カテゴリ・ステータスによるフィルタリング用のクエリメソッドを定義

### Entity 層

- **Category (用具種目)**
  - 用具種目テーブル (`categories`) にマッピング。貸与対象の13品目情報を管理する。
- **Location (保管場所)**
  - 保管場所テーブル (`locations`) にマッピング。商品の保管場所情報を管理する。
- **Manufacture (製造メーカー)**
  - メーカーテーブル (`manufactures`) にマッピング。製造メーカー情報を管理する。
- **Product (製品)**
    - 製品テーブル (`products`) にマッピング。製品ごとの情報を管理する。
- **Status (商品状態)**
    - 商品状態テーブル (`statuses`) にマッピング。商品状態 (在庫、清掃中など)の情報を管理する。
    - 商品状態は頻繁に変わることを鑑みて enum型 (`EquipmentStatus`)で管理している。
- **Stock (商品)**
    - 保有商品テーブル (`stocks`) にマッピング。会社が保有する商品の情報を管理する。

### DTO 層

- **StockDto** — 用具の一覧表示に対応

### Enum

- **EquipmentStatus** — 在庫 (`AVAILABLE`) / 貸出中 (`RENTED`) / 洗浄中 (`CLEANING`) / 修理中 (`REPAIR`)の 4 状態

## エンドポイント一覧

| メソッド | パス                       | 機能            |
|:-----|:-------------------------|:--------------|
| GET  | `/`                      | ダッシュボード       |
| GET  | `/stock`                 | 用具一覧 (フィルタ対応) |
| GET  | `/equipment/new`         | 用具登録フォーム      |
| GET  | `/equipment/{id}/edit`   | 用具編集フォーム      |
| POST | `/equipment/save`        | 用具の保存         |
| POST | `/equipment/{id}/delete` | 用具の削除         |
| GET  | `/rental`                | 貸出一覧          |
| GET  | `/rental/new`            | 貸出登録フォーム      |
| POST | `/rental/save`           | 貸出の保存         |
| POST | `/rental/{id}/return`    | 返却処理          |
# Fukushi Inventory System - 福祉用具在庫管理システム

## アプリケーションの概要

**福祉用具(車椅子・特殊寝台・歩行器など) の在庫管理を行う Web アプリケーション**

## 変更履歴

- [変更履歴はこちら (CHANGELOG.md)](./CHANGELOG.md)

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
│   ├── EquipmentController.java
│   └── RentalController.java
├── service/                           # ビジネスロジック・トランザクション管理
│   ├── EquipmentService.java
│   └── RentalService.java
├── repository/                        # データアクセス層 (Spring Data JPA)
│   ├── EquipmentRepository.java
│   └── RentalRecordRepository.java
├── entity/                            # JPA エンティティ (DB テーブルマッピング)
│   ├── Equipment.java
│   └── RentalRecord.java
├── dto/                               # データ転送オブジェクト (入力バリデーション)
│   ├── EquipmentDto.java
│   └── RentalRecordDto.java
├── enums/                             # 列挙型 (カテゴリ・ステータス定義)
│   ├── EquipmentCategory.java
│   └── EquipmentStatus.java
└── config/                            # 設定クラス (拡張用)

src/main/resources/
├── application.yml                    # アプリケーション設定
├── data.sql                           # 初期データ投入
├── static/css/style.css               # スタイルシート
└── templates/                         # Thymeleaf テンプレート
    ├── index.html                     #   ダッシュボード
    ├── fragments/layout.html          #   共通レイアウト
    ├── equipment/list.html            #   用具一覧
    ├── equipment/form.html            #   用具登録・編集フォーム
    ├── rental/list.html               #   貸出一覧
    └── rental/form.html               #   貸出登録フォーム
```

## パッケージの役割

| パッケージ | 責務 |
|-----------|------|
| `controller` | HTTP リクエストを受け取り、Service を呼び出し、テンプレートにデータを渡す |
| `service` | ビジネスルールの適用、トランザクション境界の管理、Entity と DTO の変換 |
| `repository` | Spring Data JPA による DB アクセス。カスタムクエリメソッドを定義 |
| `entity` | JPA エンティティ。DB テーブルとの O/R マッピングを担当 |
| `dto` | フォーム入力のバインディングとバリデーション制約を定義 |
| `enums` | 用具カテゴリ (8 種) とステータス (3 種) を型安全に管理 |

## 主要クラスの説明

### Controller 層

- **HomeController** — ダッシュボード画面 (`GET /`) を提供。用具の総数・ステータス別件数・貸出中件数を集計して表示する
- **EquipmentController** — 用具の CRUD 操作を担当。一覧表示（カテゴリ・ステータスでのフィルタリング対応）、新規登録・編集フォーム表示、保存、削除のエンドポイントを持つ
- **RentalController** — 貸出管理を担当。貸出一覧の表示、新規貸出登録フォーム、貸出保存、返却処理のエンドポイントを持つ

### Service 層

- **EquipmentService** — 用具の検索・保存・削除・ステータス更新を行う。`@Transactional` で読み取り専用と書き込みの境界を分離し、DTO からエンティティへの変換ロジックを集約する
- **RentalService** — 貸出のビジネスロジックを管理する。貸出時にはステータスが「在庫」であることを検証し `RENTED` へ変更、返却時には `actualReturnDate` をセットしステータスを `CLEANING` へ変更する

### Repository 層

- **EquipmentRepository** — `JpaRepository<Equipment, Long>` を継承。管理番号による検索、カテゴリ・ステータスによるフィルタリング用のクエリメソッドを定義
- **RentalRecordRepository** — `JpaRepository<RentalRecord, Long>` を継承。用具 ID による検索、未返却レコードの取得、利用者名による部分一致検索のクエリメソッドを定義

### Entity 層

- **Equipment** — 用具テーブル (`equipment`) にマッピング。管理番号・名称・カテゴリ・ステータス・説明を持ち、`RentalRecord` と `@OneToMany` で関連付けられる。作成・更新日時は Hibernate が自動管理する
- **RentalRecord** — 貸出記録テーブル (`rental_record`) にマッピング。`Equipment` と `@ManyToOne` で関連付けられ、利用者名・連絡先・貸出日・返却予定日・実返却日を管理する

### DTO 層

- **EquipmentDto** — 用具の入力フォームに対応。`@NotBlank`, `@Size`, `@NotNull` で入力値を検証する
- **RentalRecordDto** — 貸出フォームに対応。日付フィールドは `@DateTimeFormat(pattern = "yyyy-MM-dd")` でバインドされる

### Enum

- **EquipmentCategory** — 車椅子 / 特殊寝台 / 歩行器 / 手すり / スロープ / 移動用リフト / 床ずれ防止用具 / その他 の 8 種類
- **EquipmentStatus** — 在庫 (`AVAILABLE`) / 貸出中 (`RENTED`) / 洗浄中 (`CLEANING`) の 3 状態

## リクエスト処理フロー

用具の新規登録を例に、リクエストが Controller に届いてから DB に保存されるまでの流れを示します。

```
[ブラウザ] GET /equipment/new
    │
    ▼
┌─────────────────────────────────────────────────┐
│  EquipmentController.newForm()                  │
│  ・空の EquipmentDto を生成 (status = AVAILABLE) │
│  ・カテゴリ / ステータスの選択肢を Model に追加  │
│  ・"equipment/form" テンプレートを返却           │
└─────────────────────────────────────────────────┘
    │
    ▼
[ブラウザ] フォームに入力 → POST /equipment/save
    │
    ▼
┌─────────────────────────────────────────────────┐
│  EquipmentController.save()                     │
│  ・@Valid で EquipmentDto をバリデーション        │
│  ・BindingResult にエラーがあればフォームを再表示 │
│  ・エラーなし → EquipmentService.save() を呼出   │
└─────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────┐
│  EquipmentService.save()                        │
│  ・@Transactional でトランザクション開始          │
│  ・id が null → 新規 Equipment エンティティ生成  │
│  ・id が非 null → 既存エンティティを取得して更新  │
│  ・DTO のフィールドをエンティティにマッピング     │
│  ・EquipmentRepository.save() を呼出             │
└─────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────┐
│  EquipmentRepository.save()                     │
│  ・Spring Data JPA が Hibernate に委譲           │
│  ・INSERT / UPDATE SQL を H2 に発行              │
│  ・@CreationTimestamp / @UpdateTimestamp を自動設定│
│  ・トランザクション コミット                      │
└─────────────────────────────────────────────────┘
    │
    ▼
[ブラウザ] リダイレクト → GET /equipment (一覧画面)
           フラッシュメッセージ: "用具を保存しました"
```

### 貸出登録時の追加ロジック

貸出 (`RentalService.rent()`) では、上記に加えて以下のビジネスルールが適用されます。

1. 指定された用具のステータスが `AVAILABLE` でなければ `IllegalStateException` をスロー
2. `RentalRecord` エンティティを生成し、用具との関連を設定
3. 用具のステータスを `RENTED` に変更
4. `RentalRecordRepository.save()` で永続化

### 返却時の追加ロジック

返却 (`RentalService.returnEquipment()`) では以下が実行されます。

1. 対象の `RentalRecord` が既に返却済み (`actualReturnDate != null`) であれば `IllegalStateException` をスロー
2. `actualReturnDate` に当日日付をセット
3. 用具のステータスを `CLEANING` に変更

## エンドポイント一覧

| メソッド | パス | 機能 |
|---------|------|------|
| GET | `/` | ダッシュボード |
| GET | `/equipment` | 用具一覧 (フィルタ対応) |
| GET | `/equipment/new` | 用具登録フォーム |
| GET | `/equipment/{id}/edit` | 用具編集フォーム |
| POST | `/equipment/save` | 用具の保存 |
| POST | `/equipment/{id}/delete` | 用具の削除 |
| GET | `/rental` | 貸出一覧 |
| GET | `/rental/new` | 貸出登録フォーム |
| POST | `/rental/save` | 貸出の保存 |
| POST | `/rental/{id}/return` | 返却処理 |

## DB スキーマ

```
equipment                          rental_record
├── id (BIGINT, PK)                ├── id (BIGINT, PK)
├── equipment_code (VARCHAR 20)    ├── equipment_id (BIGINT, FK)
├── name (VARCHAR 100)             ├── renter_name (VARCHAR 100)
├── category (VARCHAR 30)          ├── renter_contact (VARCHAR 100)
├── status (VARCHAR 20)            ├── rental_date (DATE)
├── description (VARCHAR 500)      ├── expected_return_date (DATE)
├── created_at (TIMESTAMP)         ├── actual_return_date (DATE)
└── updated_at (TIMESTAMP)         ├── notes (VARCHAR 500)
                                   ├── created_at (TIMESTAMP)
        1 ◀──────────── * (FK)     └── updated_at (TIMESTAMP)
```

## 起動方法

```bash
# ビルド & 起動
./gradlew bootRun

# アクセス
# アプリケーション: http://localhost:8080/
# H2 コンソール:    http://localhost:8080/h2-console/
#   JDBC URL: jdbc:h2:mem:fukushi / User: sa / Password: (空)
```

## 今後のロードマップ

今後は以下の作業を行う予定です。

### 1. README.md の整備
- [ ] README.md を整備するとともにアプリケーションの構造を理解する

### 2. バグ修正
- [ ] 「貸出一覧」画面→「新規貸出」ボタン押下でエラーが発生

### 3. リファクタリング
- [ ] 保守性を高めるためControllerから返されるリスト名を〇〇sに統一

### 4. DB周辺
- [ ] PostgreSQL への移行
- [ ] Docker を導入
- [ ] MyBatis の導入

### 5. テストコードの実装
- [ ] JUnit を活用した自動テストを実装
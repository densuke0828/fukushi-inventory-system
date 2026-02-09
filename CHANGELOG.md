# アプリケーション変更履歴

<details>
<summary><b>fix: エラー修正</b> (クリックで展開)</summary>

### 2026-02-03: 用具一覧画面への遷移時に発生するエラー修正

- Thymeleafで 'th:each="eq : ${equipmentList}"' のように予約語である 'eq' を変数名としていた
    - 変数名を 'equipment (意味のある単数系名詞)' へ変更して解決

</details>

<details>
<summary><b>feat: 機能追加</b> (クリックで展開)</summary>

### 2026-02-09: schema.sqlなどのファイルでDB管理 + DB設計見直し

- Done: DB設計の見直し(data.sql, schema.sql)完了
- wip: 次回以降は新しいDBテーブル情報を反映してCRUD操作ができるように修正する

### 2026-02-08: schema.sqlなどのファイルでDB管理 + DB設計見直し

- wip: メーカーや保管場所などテーブル設計を細分化し保守しやすい設計を目指す

</details>

<details>
<summary><b>refactor: リファクタリング</b> (クリックで展開)</summary>

### 2026-02-07: MyBatisの導入

- より複雑なSQL文に対応するためにMyBatisを導入

### 2026-02-06: H2DB > PostgreSQLへ移行

- より実践的なDBを構築するためPostgreSQLへ移行: Done

### 2026-02-05: Thymeleaf Layout Dialectを使ってコードを共通化

- 子ページから個別に呼び出す形ではなくなったのでコードの見通しが良くなった

### 2026-02-04: sやcなどで変数定義していた箇所を可読性のため修正

- sやcなどで変数定義していた箇所を意味のある単数形名詞 (category など)へ修正

</details>
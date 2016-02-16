-- テストデータ
-- お題テーブル
insert into Competition values(1, "スプラトゥーチ", 100, "test");

-- タイトルテーブル
insert into title values(1, 1, "アンパンマン");

-- 管理者テーブル
insert into admin (admin_id, name, password, delete_flag) values ("mikan", "みかん", "45006", 0);

-- 利用者テーブル
insert into member values(1, "secon", "secon", "mogemoge", "secon@example.com", 19930416, "m", 0);
insert into member values(2, "mikan", "mikan", "45006", "mikan@gmail", 1992-09-25, "w", 0);
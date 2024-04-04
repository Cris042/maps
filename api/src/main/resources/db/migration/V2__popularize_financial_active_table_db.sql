INSERT INTO tb_financial_active (name, type, date_issue, date_terminus, active)
SELECT
    CONCAT('ATIVO', (ROW_NUMBER() OVER ()) - 1) AS name,
    'RV' AS type,
    '2020-01-02' AS date_issue,
    '2025-03-31' AS date_terminus,
    TRUE AS active
FROM system_range(1, 128);

INSERT INTO tb_position (name_active, type_active, amount_available, value_marketplace)
SELECT
    tf.name,
    tf.type,
    0.0 AS amount_available,
    0.0 AS value_marketplace
FROM tb_financial_active tf LEFT JOIN tb_position tp ON tf.name = tp.name_active WHERE tp.name_active IS NULL;

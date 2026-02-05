DO $$
DECLARE
    f1_id BIGINT;
    f2_id BIGINT;
    f3_id BIGINT;
    f4_id BIGINT;
BEGIN
    -- FINDINGS (idempotent)
    INSERT INTO finding (icon_title, title, description)
    VALUES ('🌍', 'Reconocimiento de la diferencia', 'La diversidad enriquece el aprendizaje.')
    ON CONFLICT (title) DO NOTHING;

    INSERT INTO finding (icon_title, title, description)
    VALUES ('🚧', 'Barreras identificadas', 'Falta de apoyo institucional, desconocimiento docente.')
    ON CONFLICT (title) DO NOTHING;

    INSERT INTO finding (icon_title, title, description)
    VALUES ('🌱', 'Prácticas exitosas', 'Estrategias socioemocionales, pedagogías del cuidado.')
    ON CONFLICT (title) DO NOTHING;

    INSERT INTO finding (icon_title, title, description)
    VALUES ('🎯', 'Retos pendientes', 'Flexibilidad curricular y formación continua.')
    ON CONFLICT (title) DO NOTHING;

    -- Obtener los IDs (si ya existen, los usamos)
    SELECT id INTO f1_id FROM finding WHERE title = 'Reconocimiento de la diferencia';
    SELECT id INTO f2_id FROM finding WHERE title = 'Barreras identificadas';
    SELECT id INTO f3_id FROM finding WHERE title = 'Prácticas exitosas';
    SELECT id INTO f4_id FROM finding WHERE title = 'Retos pendientes';

    -- MODAL_FINDING (idempotente)
    INSERT INTO modal_finding (title, description, image_path, finding_id)
    VALUES ('Reconocimiento de la diferencia', 'Los docentes participantes coinciden en que la inclusión no es un favor, sino un derecho que reconoce la diferencia como valor pedagógico. La diversidad de capacidades, ritmos y experiencias vitales potencia nuevas formas de enseñar y aprender. Este reconocimiento genera un aula más humana, donde cada estudiante es protagonista de su propio proceso y aporta a la construcción colectiva del conocimiento.', 'img/reconocimiento.png', f1_id)
    ON CONFLICT (finding_id) DO NOTHING;

    INSERT INTO modal_finding (title, description, image_path, finding_id)
    VALUES ('Barreras identificadas', 'Las entrevistas y el grupo focal revelaron que, a pesar de la normativa vigente, persisten barreras importantes: escasa formación en educación inclusiva, ausencia de recursos didácticos y sobrecarga administrativa para los docentes. La falta de acompañamiento institucional y la resistencia de algunas familias refuerzan las dificultades, dejando en manos de los maestros la mayor parte del esfuerzo de inclusión.', 'img/barreras.png', f2_id)
    ON CONFLICT (finding_id) DO NOTHING;

    INSERT INTO modal_finding (title, description, image_path, finding_id)
    VALUES ('Prácticas exitosas', 'Los docentes han desarrollado prácticas innovadoras que trascienden lo académico. Destacan las estrategias socioemocionales, el acompañamiento personalizado y las pedagogías del cuidado, que promueven el respeto, la empatía y el reconocimiento mutuo. Estas experiencias han demostrado que el vínculo afectivo y la creatividad pedagógica son claves para favorecer aprendizajes significativos y permanencia escolar.', 'img/practicas.png', f3_id)
    ON CONFLICT (finding_id) DO NOTHING;

    INSERT INTO modal_finding (title, description, image_path, finding_id)
    VALUES ('Retos pendientes', 'El desafío central identificado por los docentes es la rigidez curricular, que no siempre se adapta a los distintos ritmos de aprendizaje. Se requiere un currículo situado, flexible y capaz de responder a contextos rurales y urbanos diversos. Además, los docentes insisten en la necesidad de formación continua que les permita enfrentar los cambios normativos y pedagógicos, fortaleciendo sus competencias y asegurando procesos inclusivos sostenibles.', 'img/retos.png', f4_id)
    ON CONFLICT (finding_id) DO NOTHING;
END $$;

CREATE TABLE school (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    short_name VARCHAR(500),
    city VARCHAR(100),
    department VARCHAR(100),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE finding (
    id BIGSERIAL PRIMARY KEY,
    icon_title VARCHAR(255),
    title VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE modal_finding (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    image_path VARCHAR(500),
    finding_id BIGINT UNIQUE REFERENCES finding(id),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE interview (
    id BIGSERIAL PRIMARY KEY,
    interviewee_name VARCHAR(255) NOT NULL,
    interviewee_position VARCHAR(255),
    quote TEXT,
    school_id BIGINT REFERENCES school(id),
    url_audio VARCHAR(500),
    url_file VARCHAR(500),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE appendix (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    icon_title VARCHAR(255),
    description TEXT,
    icon_description VARCHAR(255),
    function_title VARCHAR(55),
    function_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE timeline (
    id BIGSERIAL PRIMARY KEY,
    school_id BIGINT REFERENCES school(id),
    year INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE image (
    id BIGSERIAL PRIMARY KEY,
    url_image VARCHAR(500) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);
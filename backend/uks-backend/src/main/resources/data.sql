-- USERS
INSERT INTO users (first_name, last_name, username, password, email, joined_date, password_changed, user_badge, enabled) VALUES
('John', 'Doe', 'johndoe', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'johndoe@example.com', '2024-12-01 08:00:00', false, 'VERIFIED_PUBLISHER', true),
('Jane', 'Smith', 'janesmith', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'janesmith@example.com', '2024-12-02 09:15:30', false, 'SPONSORED_OSS', true),
('Alice', 'Brown', 'alicebrown', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'alicebrown@example.com', '2024-12-03 10:30:15', false, 'VERIFIED_PUBLISHER', true),
('Bob', 'White', 'bobwhite', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'bobwhite@example.com', '2024-12-04 11:45:45', false, 'NONE', true),
('Charlie', 'Green', 'charliegreen', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'charliegreen@example.com', '2024-12-05 13:00:10', false, 'VERIFIED_PUBLISHER', true),
('Diana', 'Blue', 'dianablue', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'dianablue@example.com', '2024-12-06 14:15:20', false, 'SPONSORED_OSS', true),
('Ethan', 'Black', 'ethanblack', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'ethanblack@example.com', '2024-12-07 15:30:25', false, 'VERIFIED_PUBLISHER', true),
('Fiona', 'Gray', 'fionagray', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'fionagray@example.com', '2024-12-08 16:45:30', false, 'SPONSORED_OSS', true),
('George', 'Yellow', 'georgeyellow', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'georgeyellow@example.com', '2024-12-09 18:00:00', false, 'NONE', true),
('Hannah', 'Red', 'hannahred', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'hannahred@example.com', '2024-12-10 19:15:15', false, 'NONE', true),
('Ivan', 'Carter', 'ivancarter', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'ivancarter@example.com', '2024-12-11 10:15:30', false, 'SPONSORED_OSS', true),
('Jasmine', 'Frost', 'jasminefrost', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'jasminefrost@example.com', '2024-12-12 11:45:15', true, 'SPONSORED_OSS', true),
('Kevin', 'Stone', 'kevinstone', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'kevinstone@example.com', '2024-12-13 14:20:45', true, 'VERIFIED_PUBLISHER', true),
('Laura', 'Hill', 'laurahill', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'laurahill@example.com', '2024-12-14 08:30:10', false, 'VERIFIED_PUBLISHER', true),
('Michael', 'Ocean', 'michaelocean', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'michaelocean@example.com', '2024-12-15 09:50:00', true, 'VERIFIED_PUBLISHER', true),
('Nina', 'Sky', 'ninasky', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'ninasky@example.com', '2024-12-16 16:10:25', false, 'VERIFIED_PUBLISHER', true),
('Oscar', 'Wave', 'oscarwave', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'oscarwave@example.com', '2024-12-17 19:35:50', true, 'SPONSORED_OSS', true),
('Paula', 'Cloud', 'paulacloud', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'paulacloud@example.com', '2024-12-18 07:25:40',  false, 'SPONSORED_OSS', true),
('Quentin', 'Rain', 'quentinrain', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'quentinrain@example.com', '2024-12-19 12:00:05', true, 'SPONSORED_OSS', true),
('Rita', 'Storm', 'ritastorm', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'ritastorm@example.com', '2024-12-20 18:45:35', true, 'NONE', true),
('Sam', 'Thunder', 'samthunder', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'samthunder@example.com', '2024-12-21 10:05:15', false, 'NONE', true),
('Tina', 'Lightning', 'tinalightning', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'tinalightning@example.com', '2024-12-22 14:55:20', true, 'SPONSORED_OSS', true),
('Uma', 'Shadow', 'umashadow', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'umashadow@example.com', '2024-12-23 11:20:30', true, 'VERIFIED_PUBLISHER', true),
('Victor', 'Flame', 'victorflame', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'victorflame@example.com', '2024-12-24 09:15:40', false, 'VERIFIED_PUBLISHER', true),
('Wendy', 'Frost', 'wendyfrost', '$2a$10$X7F3hUST4aTxWHikNRY7gek7xsjW352cF51QmbRehvE9rGJooAMhW', 'wendyfrost@example.com', '2024-12-25 16:50:10', true, 'VERIFIED_PUBLISHER', true);

-- ROLES
INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_SUPER_ADMIN');

-- USER ROLE
INSERT INTO USER_ROLE (user_id, role_id) VALUES
(1, 1), (2, 1), (3, 2), (4, 3), (5, 1), (6, 1), (7, 2), (8, 1), (9, 1), (10, 1),
(11, 1), (12, 1), (13, 2), (14, 1), (15, 1), (16, 1), (17, 1), (18, 1), (19, 1), (20, 1),
(21, 2), (22, 2), (23, 1), (24, 1), (25, 2);



-- ORGANISATIONS
INSERT INTO organisations (id, name, description, owner_id, deactivated) VALUES
(1, 'Tech Innovators', 'A hub for cutting-edge technology solutions.', 1, false),
(2, 'Green Earth', 'Promoting eco-friendly practices worldwide.', 1, false),
(3, 'Health Matters', 'Innovative healthcare solutions for all.', 2, false),
(4, 'Code Creators', 'Building software for the modern age.', 3, false),
(5, 'EduFuture', 'Advancing education through technology.', 5, false),
(6, 'FinTech Pros', 'Financial solutions for the digital era.', 6, false),
(7, 'Urban Planners', 'Designing smart cities for tomorrow.', 7, false),
(8, 'Art Visionaries', 'Showcasing contemporary art globally.', 8, true),
(9, 'Sports Champs', 'Empowering young athletes worldwide.', 11, false),
(10, 'Food for All', 'Sustainable food distribution initiatives.', 11, true),
(11, 'Space Explorers', 'Exploring the final frontier.', 12, false),
(12, 'AI Thinkers', 'Researching and deploying AI technologies.', 12, false),
(13, 'Civic Builders', 'Community-driven urban development.', 13, true),
(14, 'Travel Gurus', 'Simplifying global travel experiences.', 14, false),
(15, 'Clean Energy', 'Developing renewable energy solutions.', 15, false),
(16, 'Wellness Co.', 'Innovative approaches to mental health.', 1, false),
(17, 'Game Makers', 'Creating immersive gaming experiences.', 1, false),
(18, 'Fashion Forward', 'Sustainable fashion for the future.', 2, true),
(19, 'LegalEase', 'Simplifying legal processes for businesses.', 2, false),
(20, 'Charity Connect', 'Linking donors with impactful causes.', 3, true);


--TEAMS
INSERT INTO teams (id, name, description, team_permission, organisation_id) VALUES
-- Teams for "Tech Innovators"
(1, 'Development Team', 'Responsible for software development and maintenance.', 'READ_WRITE', 1),
(2, 'Research Team', 'Focuses on innovation and exploring new technologies.', 'READ_ONLY', 1),
(3, 'Admin Team', 'Manages organizational operations and permissions.', 'ADMIN', 1),

-- Teams for "Green Earth"
(4, 'Eco Research Team', 'Conducts research on sustainable practices.', 'READ_ONLY', 2),
(5, 'Campaign Team', 'Organizes eco-awareness campaigns.', 'READ_WRITE', 2),
(6, 'Green Admin Team', 'Manages overall organizational operations.', 'ADMIN', 2),

-- Teams for "Health Matters"
(7, 'Medical Innovation Team', 'Focuses on innovative healthcare solutions.', 'READ_WRITE', 3),
(8, 'Outreach Team', 'Handles healthcare campaigns and outreach.', 'READ_ONLY', 3),
(9, 'Health Admin Team', 'Oversees operations and resource allocation.', 'ADMIN', 3),

-- Teams for "Code Creators"
(10, 'Frontend Developers', 'Develops user-facing components of software.', 'READ_WRITE', 4),
(11, 'Backend Developers', 'Manages backend systems and APIs.', 'READ_WRITE', 4),
(12, 'DevOps Team', 'Ensures seamless deployment and maintenance.', 'ADMIN', 4),

-- Teams for "EduFuture"
(13, 'EdTech Developers', 'Creates educational technology products.', 'READ_WRITE', 5),
(14, 'Teacher Liaison Team', 'Connects with educators to gather feedback.', 'READ_ONLY', 5),
(15, 'Edu Admin Team', 'Oversees team coordination and permissions.', 'ADMIN', 5),

-- Teams for "FinTech Pros"
(16, 'Product Development Team', 'Develops financial products for clients.', 'READ_WRITE', 6),
(17, 'Risk Assessment Team', 'Analyzes financial risks and mitigations.', 'READ_ONLY', 6),
(18, 'FinTech Admin Team', 'Manages permissions and organizational resources.', 'ADMIN', 6),

-- Teams for "Urban Planners"
(19, 'City Design Team', 'Designs urban layouts and infrastructure.', 'READ_WRITE', 7),
(20, 'Sustainability Team', 'Ensures sustainable urban planning practices.', 'READ_ONLY', 7),
(21, 'Urban Admin Team', 'Manages overall operations and permissions.', 'ADMIN', 7),

-- Teams for "Art Visionaries"
(22, 'Curators', 'Curates contemporary art collections.', 'READ_WRITE', 8),
(23, 'Event Planners', 'Plans and organizes art exhibitions.', 'READ_ONLY', 8),
(24, 'Art Admin Team', 'Handles administrative tasks for events.', 'ADMIN', 8),

-- Teams for "Sports Champs"
(25, 'Athlete Support Team', 'Supports young athletes with training resources.', 'READ_ONLY', 9),
(26, 'Event Management Team', 'Organizes sports tournaments and events.', 'READ_WRITE', 9),
(27, 'Sports Admin Team', 'Oversees operations and logistics.', 'ADMIN', 9),

-- Teams for "Food for All"
(28, 'Distribution Team', 'Manages sustainable food distribution.', 'READ_WRITE', 10),
(29, 'Community Outreach Team', 'Collaborates with local communities.', 'READ_ONLY', 10),
(30, 'Food Admin Team', 'Supervises logistics and team operations.', 'ADMIN', 10),

-- Teams for "Space Explorers"
(31, 'Mission Control', 'Manages space exploration missions.', 'READ_WRITE', 11),
(32, 'Research Team of Space', 'Conducts research on space technologies.', 'READ_ONLY', 11),
(33, 'Space Admin Team', 'Handles administrative operations.', 'ADMIN', 11),

-- Teams for "AI Thinkers"
(34, 'AI Research Team', 'Explores AI algorithms and solutions.', 'READ_WRITE', 12),
(35, 'Data Collection Team', 'Prepares datasets for AI research.', 'READ_ONLY', 12),
(36, 'AI Admin Team', 'Supervises project coordination.', 'ADMIN', 12),

-- Teams for "Civic Builders"
(37, 'Urban Development Team', 'Manages urban construction projects.', 'READ_WRITE', 13),
(38, 'Community Relations Team', 'Engages with local communities for feedback.', 'READ_ONLY', 13),
(39, 'Civic Admin Team', 'Handles organizational operations and strategy.', 'ADMIN', 13),

-- Teams for "Travel Gurus"
(40, 'Customer Support Team', 'Provides support to travelers globally.', 'READ_ONLY', 14),
(41, 'Product Development Team of Travel', 'Builds tools for seamless travel experiences.', 'READ_WRITE', 14),
(42, 'Travel Admin Team', 'Oversees travel operations and management.', 'ADMIN', 14);



-- TEAMS_MEMBERS
-- Teams for "Tech Innovators"
INSERT INTO teams_members (team_id, member_id) VALUES
(1, 3), -- Development Team: Alice Brown
(1, 5), -- Development Team: Charlie Green
(1, 7), -- Development Team: Ethan Black

(2, 2), -- Research Team: Jane Smith
(2, 6), -- Research Team: Diana Blue
(2, 4), -- Research Team: Fiona Gray

(3, 1), -- Admin Team: John Doe (Owner)
(3, 8), -- Admin Team: Bob White
(3, 11), -- Admin Team: Ivan Carter

-- Teams for "Green Earth"
(4, 9), -- Eco Research Team: George Yellow
(4, 10), -- Eco Research Team: Hannah Red
(4, 5), -- Eco Research Team: Charlie Green

(5, 3), -- Campaign Team: Alice Brown
(5, 11), -- Campaign Team: Ivan Carter
(5, 14), -- Campaign Team: Laura Hill

(6, 2), -- Green Admin Team: Jane Smith
(6, 12), -- Green Admin Team: Jasmine Fros
(6, 1), -- Green Admin Team: John Doe (Owner)

-- Teams for "Health Matters"
(7, 21), -- Medical Innovation Team: Sam Thunder
(7, 14), -- Medical Innovation Team: Laura Hill
(7, 5), -- Medical Innovation Team: Charlie Green

(8, 15), -- Outreach Team: Michael Ocean
(8, 9), -- Outreach Team: George Yellow
(8, 18), -- Outreach Team: Paula Cloud

(9, 2), -- Health Admin Team: Jane Smith (Owner)
(9, 3), -- Health Admin Team: Alice Brown
(9, 13), -- Health Admin Team: Kevin Stone

-- Teams for "Code Creators"
(10, 1), -- Frontend Developers: John Doe
(10, 13), -- Frontend Developers: Kevin Stone
(10, 17), -- Frontend Developers: Oscar Wave

(11, 4), -- Backend Developers: Bob White
(11, 9), -- Backend Developers: George Yellow
(11, 21), -- Backend Developers: Sam Thunder

(12, 3), -- DevOps Team: Alice Brown (Owner)
(12, 5), -- DevOps Team: Charlie Green

-- Teams for "EduFuture"
(13, 17), -- EdTech Developers: Oscar Wave
(13, 3), -- EdTech Developers: Alice Brown
(13, 13), -- EdTech Developers: Kevin Stone

(14, 12), -- Teacher Liaison Team: Jasmine Frost
(14, 9), -- Teacher Liaison Team: George Yellow
(14, 14), -- Teacher Liaison Team: Laura Hill

(15, 5), -- Edu Admin Team: Charlie Green (Owner)
(15, 6), -- Edu Admin Team: Diana Blue
(15, 8), -- Edu Admin Team: Bob White

-- Teams for "FinTech Pros"
(16, 10), -- Product Development Team: Hannah Red
(16, 19), -- Product Development Team: Quentin Rain
(16, 4), -- Product Development Team: Bob White

(17, 16), -- Risk Assessment Team: Nina Sky
(17, 5), -- Risk Assessment Team: Charlie Green
(17, 11), -- Risk Assessment Team: Ivan Carter

(18, 6), -- FinTech Admin Team: Diana Blue (Owner)
(18, 8), -- FinTech Admin Team: Fiona Gray
(18, 15), -- FinTech Admin Team: Michael Ocean

-- Teams for "Urban Planners"
(19, 1), -- City Design Team: John Doe
(19, 2), -- City Design Team: Jane Smith
(19, 12), -- City Design Team: Jasmine Frost

(20, 21), -- Sustainability Team: Sam Thunder
(20, 16), -- Sustainability Team: Nina Sky
(20, 19), -- Sustainability Team: Quentin Rain

(21, 7), -- Urban Admin Team: Ethan Black (Owner)
(21, 5), -- Urban Admin Team: Charlie Green
(21, 17), -- Urban Admin Team: Oscar Wave

-- Teams for "Art Visionaries"
(22, 11), -- Curators: Ivan Carter
(22, 2), -- Curators: Jane Smith
(22, 12), -- Curators: Jasmine Frost

(23, 13), -- Event Planners: Kevin Stone
(23, 3), -- Event Planners: Alice Brown
(23, 18), -- Event Planners: Paula Cloud

(24, 8), -- Art Admin Team: Fiona Gray (Owner)
(24, 16), -- Art Admin Team:  Nina Sky
(24, 14), -- Art Admin Team: Laura Hill

-- Teams for "Sports Champs"
(25, 15), -- Athlete Support Team: Michael Ocean
(25, 6), -- Athlete Support Team: Diana Blue
(25, 14), -- Athlete Support Team: Laura Hill

(26, 1), -- Event Management Team: John Doe
(26, 21), -- Event Management Team: Sam Thunder
(26, 20), -- Event Management Team: Rita Storm

(27, 11), -- Sports Admin Team: Ivan Carter (Owner)
(27, 17), -- Sports Admin Team: Oscar Wave
(27, 16), -- Sports Admin Team: Nina Sky

-- Teams for "Food for All"
(28, 5), -- Distribution Team: Charlie Green
(28, 10), -- Distribution Team: Hannah Red
(28, 13), -- Distribution Team: Kevin Stone

(29, 17), -- Community Outreach Team: Oscar Wave
(29, 6), -- Community Outreach Team: Diana Blue
(29, 15), -- Community Outreach Team: Michael Ocean

(30, 11), -- Food Admin Team: Ivan Carter (Owner)
(30, 18), -- Food Admin Team: Paula Cloud

-- Teams for "Space Explorers"
(31, 3), -- Mission Control: Alice Brown
(31, 16), -- Mission Control: Nina Sky
(31, 17), -- Mission Control: Oscar Wave

(32, 14), -- Research Team: Laura Hill
(32, 7), -- Research Team: Ethan Black
(32, 22), -- Research Team: Tina Lightning

(33, 12), -- Space Admin Team: Jasmine Frost (Owner)
(33, 18), -- Space Admin Team: Paula Cloud

-- Teams for "AI Thinkers"
(34, 19), -- AI Research Team: Quentin Rain
(34, 21), -- AI Research Team: Sam Thunder
(34, 6), -- AI Research Team: Diana Blue

(35, 4), -- Data Collection Team: Bob White
(35, 9), -- Data Collection Team: George Yellow
(35, 13), -- Data Collection Team: Kevin Stone

(36, 12), -- Space Admin Team: Jasmine Frost (Owner)
(36, 18), -- Space Admin Team: Paula Cloud

-- Teams for "Civic Builders"
(37, 10), -- Urban Development Team: Hannah Red
(37, 5), -- Urban Development Team: Charlie Green
(37, 8), -- Urban Development Team: Fiona Gray

(38, 9), -- Community Relations Team: George Yellow
(38, 14), -- Community Relations Team: Laura Hill
(38, 20), -- Community Relations Team: Rita Storm

(39, 13), -- Civic Admin Team: Alice Brown (Owner)
(39, 21), -- Civic Admin Team: Sam Thunder


-- Teams for "Travel Gurus"
(40, 10), -- Urban Development Team: Hannah Red
(40, 5), -- Urban Development Team: Charlie Green
(40, 8), -- Urban Development Team: Fiona Gray

(41, 9), -- Community Relations Team: George Yellow
(41, 20), -- Community Relations Team: Rita Storm

(42, 14), -- Civic Admin Team: Laura Hill (Owner)
(42, 22); -- Civic Admin Team: Tina Lightning



-- ORGANISATION_MEMBERS
INSERT INTO organisations_members (organisation_id, member_id) VALUES
(1, 3), (1, 5), (1, 7), (1, 2), (1, 6), (1, 4), (1, 1), (1, 8), (1, 11),
(2, 9), (2, 10), (2, 5), (2, 3), (2, 11), (2, 14), (2, 2), (2, 12), (2, 1),
(3, 21), (3, 14), (3, 5), (3, 15), (3, 9), (3, 18), (3, 2), (3, 3), (3, 13),
(4, 1), (4, 13), (4, 17), (4, 4), (4, 9), (4, 21), (4, 3), (4, 5),
(5, 17), (5, 3), (5, 13), (5, 12), (5, 9), (5, 14), (5, 5), (5, 6), (5, 8),
(6, 10), (6, 19), (6, 14), (6, 16), (6, 5), (6, 11), (6, 6), (6, 8), (6, 15),
(7, 1), (7, 2), (7, 12), (7, 21), (7, 16), (7, 19), (7, 7), (7, 5), (7, 17),
(8, 11), (8, 2), (8, 12), (8, 13), (8, 3), (8, 18), (8, 8), (8, 16), (8, 14),
(9, 15), (9, 6), (9, 14), (9, 1), (9, 21), (9, 20), (9, 11), (9, 17), (9, 16),
(10, 5), (10, 10), (10, 13), (10, 17), (10, 6), (10, 15), (10, 11), (10, 18),
(11, 3), (11, 16), (11, 17), (11, 14), (11, 7), (11, 22), (11, 12), (11, 18),
(12, 19), (12, 21), (12, 6), (12, 4), (12, 9), (12, 13), (12, 12), (12, 18),
(13, 10), (13, 5), (13, 8), (13, 9), (13, 14), (13, 20), (13, 13), (13, 21),
(14, 10), (14, 5), (14, 8), (14, 9), (14, 20), (14, 14), (14, 22),
(15, 15), (15, 23),
(16, 1), (16, 24),
(17, 1), (17, 25),
(18, 2), (18, 1),
(19, 2), (19, 1),
(20, 3), (20, 2);


--REPOS WITHOUT ORGANISATION 7, 13, 19, 29, 37, 39 = 6


-- REPOSITORIES
INSERT INTO repositories (name, namespace, description, visibility, created, updated, star, personal, category, owner_id, type, prefix, badge, organisation_id, deleted) VALUES
('WeatherAPI', 'api-tools', 'Weather forecasting API tool', 'PUBLIC', '2024-12-01 10:00:00', '2024-12-01 12:00:00', 85, true, 'API_MANAGMENT', 1, 'Repository', NULL, 'NONE', 1, false),
('CMSBuilder', 'content-dev', 'Content management system for blogs', 'PRIVATE', '2024-12-02 14:00:00', '2024-12-02 15:30:00', 42, false, 'CONTENT_MANAGMENT_SYSTEM', 2, 'Official', 'OFF-001', 'DOCKER_OFFICIAL_IMAGE', 2, false),
('DataAnalyzer', 'data-tools', 'Data science library for analytics', 'PUBLIC', '2024-12-03 08:30:00', '2024-12-03 09:45:00', 110, true, 'DATA_SCIENCE', 3, 'Repository', NULL, 'NONE', 3, false),
('SecureStorage', 'db-storage', 'Secure database storage solutions', 'PRIVATE', '2024-12-04 09:00:00', '2024-12-04 11:20:00', 78, false, 'DATABASE_AND_STORAGE', 5, 'Official', 'OFF-002', 'DOCKER_OFFICIAL_IMAGE', 4, false),
('PythonFramework', 'lang-lib', 'Python framework for web apps', 'PUBLIC', '2024-12-05 13:45:00', '2024-12-05 14:15:00', 200, true, 'LANGUAGE_AND_FRAMEWORKS', 5, 'Repository', NULL, 'NONE', 4, false),
('IoTPlatform', 'iot-dev', 'IoT platform for smart devices', 'PRIVATE', '2024-12-06 07:00:00', '2024-12-06 08:10:00', 54, false, 'INTERNET_OF_THINGS', 6, 'Official', 'OFF-003', 'DOCKER_OFFICIAL_IMAGE', 5, false),
('MLToolkit', 'ai-tools', 'Machine learning toolkit for developers', 'PUBLIC', '2024-12-07 09:30:00', '2024-12-07 10:45:00', 130, true, 'MACHINE_LEARNING_AND_AI', 7, 'Repository', NULL, 'NONE', null, false),
('CloudStorage', 'cloud-solutions', 'Cloud storage solutions for enterprises', 'PRIVATE', '2024-12-08 12:15:00', '2024-12-08 14:00:00', 95, false, 'DATABASE_AND_STORAGE', 8, 'Official', 'OFF-004', 'DOCKER_OFFICIAL_IMAGE', 6, false),
('DevOpsPipeline', 'devops-tools', 'Continuous integration and deployment pipeline', 'PUBLIC', '2024-12-09 15:00:00', '2024-12-09 16:30:00', 88, true, 'DEVOPS', 8, 'Repository', NULL, 'NONE', 6, false),
('WebServerFramework', 'web-server', 'Web server framework for fast deployments', 'PRIVATE', '2024-12-10 11:30:00', '2024-12-10 13:00:00', 210, true, 'WEB_SERVERS', 8, 'Official', 'OFF-005', 'DOCKER_OFFICIAL_IMAGE', 1, false),
('DataPipeline', 'data-processing', 'Data pipeline tools for processing large datasets', 'PUBLIC', '2024-12-11 14:30:00', '2024-12-11 15:45:00', 115, false, 'INTEGRATION_AND_DELIVERY', 11, 'Repository', NULL, 'NONE', 1, false),
('SecurityToolkit', 'security-tools', 'Security tools for system administrators', 'PRIVATE', '2024-12-12 16:45:00', '2024-12-12 18:00:00', 67, false, 'SECURITY', 12, 'Official', 'OFF-006', 'DOCKER_OFFICIAL_IMAGE', 2, false),
('MessagingQueue', 'message-queues', 'Message queues for microservice communication', 'PUBLIC', '2024-12-13 10:00:00', '2024-12-13 11:20:00', 150, true, 'MESSAGE_QUEUES', 13, 'Repository', NULL, 'NONE', 3, false),
('MentoringPlatform', 'mentoring-tools', 'Online mentoring platform for developers', 'PRIVATE', '2024-12-14 13:30:00', '2024-12-14 14:40:00', 102, false, 'MENTORING_AND_OBSERVABILITY', 14, 'Official', 'OFF-007', 'DOCKER_OFFICIAL_IMAGE', null, false),
('NetworkingTools', 'networking-dev', 'Networking tools for large-scale networks', 'PUBLIC', '2024-12-15 09:45:00', '2024-12-15 11:00:00', 80, true, 'NETWORKING', 15, 'Repository', NULL, 'NONE', 6, false),
('OSLibrary', 'os-tools', 'Operating system utilities for developers', 'PRIVATE', '2024-12-16 14:00:00', '2024-12-16 15:10:00', 50, false, 'OPERATING_SYSTEM', 16, 'Official', 'OFF-008', 'DOCKER_OFFICIAL_IMAGE', 8, false),
('ObservabilityTools', 'observability', 'Tools for monitoring and observability', 'PUBLIC', '2024-12-17 12:30:00', '2024-12-17 14:10:00', 120, true, 'OBSERVABILITY', 17, 'Repository', NULL, 'NONE', 9, false),
('SecureAPI', 'api-security', 'Secure API authentication and authorization', 'PRIVATE', '2024-12-18 08:15:00', '2024-12-18 09:30:00', 95, false, 'SECURITY', 18, 'Official', 'OFF-009', 'DOCKER_OFFICIAL_IMAGE', 10, false),
('DockerExtension', 'docker-tools', 'Extensions for enhancing Docker workflows', 'PUBLIC', '2024-12-19 11:00:00', '2024-12-19 12:45:00', 130, true, 'DOCKER_EXTENSIONS', 18, 'Repository', NULL, 'NONE', 11, false),
('CloudAPI', 'cloud-tools', 'API for integrating with cloud services', 'PRIVATE', '2024-12-20 09:30:00', '2024-12-20 10:45:00', 200, true, 'API_MANAGMENT', 18, 'Official', 'OFF-010', 'DOCKER_OFFICIAL_IMAGE', 12, false),
('FrontendFramework', 'web-dev', 'Frontend framework for modern web apps', 'PUBLIC', '2024-12-21 14:00:00', '2024-12-21 15:30:00', 180, true, 'FRONTEND', 21, 'Repository', NULL, 'NONE', 13, false),
('PythonTools', 'python-utils', 'Tools for Python developers', 'PRIVATE', '2024-12-22 13:30:00', '2024-12-22 14:55:00', 110, false, 'LANGUAGE_AND_FRAMEWORKS', 22, 'Official', 'OFF-011', 'DOCKER_OFFICIAL_IMAGE', 14, false),
('JavaFramework', 'java-lib', 'Java library for backend apps', 'PUBLIC', '2024-12-23 12:00:00', '2024-12-23 13:25:00', 95, true, 'LANGUAGE_AND_FRAMEWORKS', 23, 'Repository', NULL, 'NONE', 15, false),
('DatabaseTools', 'db-dev', 'Tools for managing databases', 'PRIVATE', '2024-12-24 09:45:00', '2024-12-24 11:10:00', 130, false, 'DATABASE_AND_STORAGE', 24, 'Official', 'OFF-012', 'DOCKER_OFFICIAL_IMAGE', 16, false),
('AIPlatform', 'ai-development', 'Platform for building AI models', 'PUBLIC', '2024-12-25 10:00:00', '2024-12-25 11:30:00', 150, true, 'MACHINE_LEARNING_AND_AI', 25, 'Repository', NULL, 'NONE', 17, false),
('BackupTools', 'backup-dev', 'Tools for automated backups', 'PRIVATE', '2024-12-26 08:15:00', '2024-12-26 09:40:00', 50, false, 'DATABASE_AND_STORAGE', 1, 'Official', 'OFF-013', 'DOCKER_OFFICIAL_IMAGE', 18, false),
('RealTimeAnalytics', 'realtime-data', 'Real-time data processing and analytics', 'PUBLIC', '2024-12-27 14:30:00', '2024-12-27 15:55:00', 120, true, 'DATA_SCIENCE', 1, 'Repository', NULL, 'NONE', 19, false),
('WebCrawler', 'web-tools', 'Web crawler for data scraping', 'PRIVATE', '2024-12-28 11:00:00', '2024-12-28 12:15:00', 70, false, 'DEVOPS', 2, 'Official', 'OFF-014', 'DOCKER_OFFICIAL_IMAGE', 20, false),
('DatabaseBackup', 'db-tools', 'Backup solutions for databases', 'PUBLIC', '2024-12-29 09:30:00', '2024-12-29 11:05:00', 65, true, 'DATABASE_AND_STORAGE', 2, 'Repository', NULL, 'NONE', null, false),
('VirtualizationTools', 'virtualization-dev', 'Tools for virtualized environments', 'PRIVATE', '2024-12-30 10:00:00', '2024-12-30 11:25:00', 90, false, 'DATABASE_AND_STORAGE', 3, 'Official', 'OFF-015', 'DOCKER_OFFICIAL_IMAGE', null, false),
('WebSocketServer', 'web-server', 'WebSocket server implementation for apps', 'PUBLIC', '2024-12-31 13:30:00', '2024-12-31 14:40:00', 150, true, 'WEB_SERVERS',3, 'Repository', NULL, 'NONE', 10, false),
('AuthenticationService', 'auth-service', 'Service for handling user authentication', 'PRIVATE', '2024-11-01 08:45:00', '2024-11-01 10:05:00', 55, false, 'SECURITY', 3, 'Official', 'OFF-016', 'DOCKER_OFFICIAL_IMAGE', 9, false),
('ServerManagement', 'server-admin', 'Tools for server management and monitoring', 'PUBLIC', '2024-11-02 10:15:00', '2024-11-02 11:25:00', 100, true, 'SECURITY', 5, 'Repository', NULL, 'NONE', 8, false),
('DataWarehouse', 'big-data', 'Warehouse for storing large datasets', 'PRIVATE', '2024-11-03 09:00:00', '2024-11-03 10:30:00', 130, false, 'DATA_SCIENCE', 5, 'Official', 'OFF-017', 'DOCKER_OFFICIAL_IMAGE', 7, false),
('FileCompression', 'file-utils', 'Tools for compressing files and archives', 'PUBLIC', '2024-11-04 10:40:00', '2024-11-04 12:10:00', 40, true, 'OPERATING_SYSTEM', 6, 'Repository', NULL, 'NONE', 6, true),
('TaskScheduler', 'task-management', 'Task scheduling and automation system', 'PRIVATE', '2024-11-05 08:00:00', '2024-11-05 09:15:00', 98, false, 'OPERATING_SYSTEM', 6, 'Official', 'OFF-018', 'DOCKER_OFFICIAL_IMAGE', 5, true),
('APIIntegration', 'api-tools', 'API integration for external systems', 'PUBLIC', '2024-11-06 13:30:00', '2024-11-06 14:45:00', 160, true, 'API_MANAGMENT', 7, 'Repository', NULL, 'NONE', null, true),
('TestAutomation', 'automation-tools', 'Automated testing tools for apps', 'PRIVATE', '2024-11-07 09:45:00', '2024-11-07 11:00:00', 120, false, 'OPERATING_SYSTEM', 7, 'Official', 'OFF-019', 'DOCKER_OFFICIAL_IMAGE', null, true),
('SystemMonitoring', 'system-admin', 'Tools for system and performance monitoring', 'PUBLIC', '2024-11-08 08:10:00', '2024-11-08 09:20:00', 80, true, 'OPERATING_SYSTEM', 8, 'Repository', NULL, 'NONE', null, true),
('APIProxy', 'api-gateway', 'API gateway for routing requests', 'PRIVATE', '2024-11-09 10:30:00', '2024-11-09 11:40:00', 70, false, 'API_MANAGMENT', 8, 'Official', 'OFF-020', 'DOCKER_OFFICIAL_IMAGE', null, true);



-- TAGS
INSERT INTO tags (id, name, docker_pull_command, repository_id) VALUES
  (1, 'weather-api', 'docker pull api-tools/weather-api', 1),
  (2, 'forecasting', 'docker pull api-tools/forecasting', 1),
  (3, 'weather', 'docker pull api-tools/weather', 1),
  (4, 'api-tool', 'docker pull api-tools/api-tool', 1),
  (5, 'climate', 'docker pull api-tools/climate', 1),
  (6, 'cms-builder', 'docker pull content-dev/cms-builder', 2),
  (7, 'blog-cms', 'docker pull content-dev/blog-cms', 2),
  (8, 'content-management', 'docker pull content-dev/content-management', 2),
  (9, 'cms-core', 'docker pull content-dev/cms-core', 2),
  (10, 'private-cms', 'docker pull content-dev/private-cms', 2),
  (11, 'blogging-platform', 'docker pull content-dev/blogging-platform', 2),
  (12, 'content-dev-tool', 'docker pull content-dev/content-dev-tool', 2),
  (13, 'data-analyzer', 'docker pull data-tools/data-analyzer', 3),
  (14, 'analytics', 'docker pull data-tools/analytics', 3),
  (15, 'data-science', 'docker pull data-tools/data-science', 3),
  (16, 'data-tools', 'docker pull data-tools/data-tools', 3),
  (17, 'machine-learning', 'docker pull data-tools/machine-learning', 3),
  (18, 'ai-analytics', 'docker pull data-tools/ai-analytics', 3),
  (19, 'big-data', 'docker pull data-tools/big-data', 3),
  (20, 'secure-storage', 'docker pull db-storage/secure-storage', 4),
  (21, 'database-security', 'docker pull db-storage/database-security', 4),
  (22, 'encrypted-db', 'docker pull db-storage/encrypted-db', 4),
  (23, 'secure-db', 'docker pull db-storage/secure-db', 4),
  (24, 'storage-solutions', 'docker pull db-storage/storage-solutions', 4),
  (25, 'private-db', 'docker pull db-storage/private-db', 4),
  (26, 'safe-storage', 'docker pull db-storage/safe-storage', 4),
  (27, 'python-framework', 'docker pull lang-lib/python-framework', 5),
  (28, 'web-framework', 'docker pull lang-lib/web-framework', 5),
  (29, 'python-web', 'docker pull lang-lib/python-web', 5),
  (30, 'backend-framework', 'docker pull lang-lib/backend-framework', 5),
  (31, 'language-library', 'docker pull lang-lib/language-library', 5),
  (32, 'fast-python', 'docker pull lang-lib/fast-python', 5),
  (33, 'framework-tools', 'docker pull lang-lib/framework-tools', 5),
  (34, 'iot-platform', 'docker pull iot-dev/iot-platform', 6),
  (35, 'smart-devices', 'docker pull iot-dev/smart-devices', 6),
  (36, 'iot-dev', 'docker pull iot-dev/iot-dev', 6),
  (37, 'device-management', 'docker pull iot-dev/device-management', 6),
  (38, 'iot-solutions', 'docker pull iot-dev/iot-solutions', 6),
  (39, 'connected-devices', 'docker pull iot-dev/connected-devices', 6),
  (40, 'iot-tools', 'docker pull iot-dev/iot-tools', 6),
  (41, 'ml-toolkit', 'docker pull ai-tools/ml-toolkit', 7),
  (42, 'ai-development', 'docker pull ai-tools/ai-development', 7),
  (43, 'ml-library', 'docker pull ai-tools/ml-library', 7),
  (44, 'ai-tools', 'docker pull ai-tools/ai-tools', 7),
  (45, 'deep-learning', 'docker pull ai-tools/deep-learning', 7),
  (46, 'ai-solutions', 'docker pull ai-tools/ai-solutions', 7),
  (47, 'ml-development', 'docker pull ai-tools/ml-development', 7),
  (48, 'cloud-storage', 'docker pull cloud-solutions/cloud-storage', 8),
  (49, 'enterprise-storage', 'docker pull cloud-solutions/enterprise-storage', 8),
  (50, 'db-storage', 'docker pull cloud-solutions/db-storage', 8),
  (51, 'secure-cloud', 'docker pull cloud-solutions/secure-cloud', 8),
  (52, 'cloud-solutions', 'docker pull cloud-solutions/cloud-solutions', 8),
  (53, 'data-storage', 'docker pull cloud-solutions/data-storage', 8),
  (54, 'backup-solutions', 'docker pull cloud-solutions/backup-solutions', 8),
  (55, 'devops-pipeline', 'docker pull devops-tools/devops-pipeline', 9),
  (56, 'ci-cd', 'docker pull devops-tools/ci-cd', 9),
  (57, 'automation-tools', 'docker pull devops-tools/automation-tools', 9),
  (58, 'deployment-pipeline', 'docker pull devops-tools/deployment-pipeline', 9),
  (59, 'devops-tools', 'docker pull devops-tools/devops-tools', 9),
  (60, 'continuous-delivery', 'docker pull devops-tools/continuous-delivery', 9),
  (61, 'integration-tools', 'docker pull devops-tools/integration-tools', 9),
  (62, 'web-server-framework', 'docker pull web-server/web-server-framework', 10),
  (63, 'web-server', 'docker pull web-server/web-server', 10),
  (64, 'deployment-tools', 'docker pull web-server/deployment-tools', 10),
  (65, 'fast-deployment', 'docker pull web-server/fast-deployment', 10),
  (66, 'server-framework', 'docker pull web-server/server-framework', 10),
  (67, 'scalable-server', 'docker pull web-server/scalable-server', 10),
  (68, 'web-app-tools', 'docker pull web-server/web-app-tools', 10),
  (69, 'data-pipeline', 'docker pull data-processing/data-pipeline', 11),
  (70, 'large-dataset-tools', 'docker pull data-processing/large-dataset-tools', 11),
  (71, 'data-processing', 'docker pull data-processing/data-processing', 11),
  (72, 'security-toolkit', 'docker pull security-tools/security-toolkit', 12),
  (73, 'sysadmin-tools', 'docker pull security-tools/sysadmin-tools', 12),
  (74, 'system-security', 'docker pull security-tools/system-security', 12),
  (75, 'messaging-queue', 'docker pull message-queues/messaging-queue', 13),
  (76, 'microservices-communication', 'docker pull message-queues/microservices-communication', 13),
  (77, 'message-queues', 'docker pull message-queues/message-queues', 13),
  (78, 'mentoring-platform', 'docker pull mentoring-tools/mentoring-platform', 14),
  (79, 'developer-tools2', 'docker pull mentoring-tools/developer-tools', 14),
  (80, 'mentoring-tools', 'docker pull mentoring-tools/mentoring-tools', 14),
  (81, 'networking-tools', 'docker pull networking-dev/networking-tools', 15),
  (82, 'large-scale-networks', 'docker pull networking-dev/large-scale-networks', 15),
  (83, 'networking-dev', 'docker pull networking-dev/networking-dev', 15),
  (84, 'os-library', 'docker pull os-tools/os-library', 16),
  (85, 'os-utilities', 'docker pull os-tools/os-utilities', 16),
  (86, 'os-tools', 'docker pull os-tools/os-tools', 16),
  (87, 'observability-tools', 'docker pull observability/observability-tools', 17),
  (88, 'monitoring-tools', 'docker pull observability/monitoring-tools', 17),
  (89, 'observability', 'docker pull observability/observability', 17),
  (90, 'secure-api', 'docker pull api-security/secure-api', 18),
  (91, 'api-authentication', 'docker pull api-security/api-authentication', 18),
  (92, 'api-security', 'docker pull api-security/api-security', 18),
  (93, 'docker-extension', 'docker pull docker-tools/docker-extension', 19),
  (94, 'docker-enhancements', 'docker pull docker-tools/docker-enhancements', 19),
  (95, 'docker-tools', 'docker pull docker-tools/docker-tools', 19),
  (96, 'cloud-api', 'docker pull cloud-tools/cloud-api', 20),
  (97, 'cloud-integration', 'docker pull cloud-tools/cloud-integration', 20),
  (98, 'cloud-tools', 'docker pull cloud-tools/cloud-tools', 20),
  (99, 'modern-web-apps', 'docker pull web-dev/modern-web-apps', 21),
  (100, 'frontend-framework', 'docker pull web-dev/frontend-framework', 21),
  (101, 'python-tools', 'docker pull python-utils/python-tools', 22),
  (102, 'developer-tools3', 'docker pull python-utils/developer-tools3', 22),
  (103, 'java-library', 'docker pull java-lib/java-library', 23),
  (104, 'backend-tools', 'docker pull java-lib/backend-tools', 23),
  (105, 'db-tools', 'docker pull db-dev/db-tools', 24),
  (106, 'database-management', 'docker pull db-dev/database-management', 24),
  (107, 'ai-platform', 'docker pull ai-development/ai-platform', 25),
  (108, 'ml-development2', 'docker pull ai-development/ml-development2', 25),
  (109, 'automated-backups', 'docker pull backup-dev/automated-backups', 26),
  (110, 'backup-tools', 'docker pull backup-dev/backup-tools', 26),
  (111, 'real-time-data', 'docker pull realtime-data/real-time-data', 27),
  (112, 'data-analytics', 'docker pull realtime-data/data-analytics', 27),
  (113, 'web-crawling', 'docker pull web-tools/web-crawling', 28),
  (114, 'data-scraping', 'docker pull web-tools/data-scraping', 28),
  (115, 'database-backups', 'docker pull db-tools/database-backups', 29),
  (116, 'backup-solutions2', 'docker pull db-tools/backup-solutions2', 29),
  (117, 'virtualization-tools', 'docker pull virtualization-dev/virtualization-tools', 30),
  (118, 'virtualized-environments', 'docker pull virtualization-dev/virtualized-environments', 30),
  (119, 'websockets2', 'docker pull web-server/websockets2', 31),
  (120, 'web-server-framework2', 'docker pull web-server/web-server-framework2', 31),
  (121, 'auth-service', 'docker pull auth-service/auth-service', 32),
  (122, 'user-authentication', 'docker pull auth-service/user-authentication', 32),
  (123, 'server-management', 'docker pull server-admin/server-management', 33),
  (124, 'monitoring-tools2', 'docker pull server-admin/monitoring-tools2', 33),
  (125, 'data-warehouse', 'docker pull big-data/data-warehouse', 34),
  (126, 'big-data-tools', 'docker pull big-data/big-data-tools', 34),
  (127, 'file-compression', 'docker pull file-utils/file-compression', 35),
  (128, 'archive-tools', 'docker pull file-utils/archive-tools', 35),
  (129, 'task-scheduler', 'docker pull task-management/task-scheduler', 36),
  (130, 'automation-tools4', 'docker pull task-management/automation-tools4', 36),
  (131, 'api-integration', 'docker pull api-tools/api-integration', 37),
  (132, 'external-systems', 'docker pull api-tools/external-systems', 37),
  (133, 'test-automation', 'docker pull automation-tools/test-automation', 38),
  (134, 'quality-assurance', 'docker pull automation-tools/quality-assurance', 38),
  (135, 'system-monitoring', 'docker pull system-admin/system-monitoring', 39),
  (136, 'performance-tools', 'docker pull system-admin/performance-tools', 39),
  (137, 'api-proxy', 'docker pull api-gateway/api-proxy', 40),
  (138, 'request-routing', 'docker pull api-gateway/request-routing', 40);




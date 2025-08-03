
-- USERS
INSERT INTO users (first_name, last_name, username, password, email, joined_date, role, password_changed, user_badge, enabled, last_password_reset_date) VALUES
('John', 'Doe', 'johndoe', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'johndoe@example.com', '2024-12-01 08:00:00', 'USER', false, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Jane', 'Smith', 'janesmith', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'janesmith@example.com', '2024-12-02 09:15:30', 'USER', false, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Alice', 'Brown', 'alicebrown', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'alicebrown@example.com', '2024-12-03 10:30:15', 'ADMIN', false, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Bob', 'White', 'bobwhite', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'bobwhite@example.com', '2024-12-04 11:45:45', 'ADMIN', false, 'NONE', true, '2017-10-01 21:58:58.508-07'),
('Charlie', 'Green', 'charliegreen', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'charliegreen@example.com', '2024-12-05 13:00:10', 'USER', false, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Diana', 'Blue', 'dianablue', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'dianablue@example.com', '2024-12-06 14:15:20', 'USER', false, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Ethan', 'Black', 'ethanblack', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'ethanblack@example.com', '2024-12-07 15:30:25', 'ADMIN', false, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Fiona', 'Gray', 'fionagray', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'fionagray@example.com', '2024-12-08 16:45:30', 'USER', false, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('George', 'Yellow', 'georgeyellow', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'georgeyellow@example.com', '2024-12-09 18:00:00', 'USER', false, 'NONE', true, '2017-10-01 21:58:58.508-07'),
('Hannah', 'Red', 'hannahred', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'hannahred@example.com', '2024-12-10 19:15:15', 'USER', false, 'NONE', true, '2017-10-01 21:58:58.508-07'),
('Ivan', 'Carter', 'ivancarter', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'ivancarter@example.com', '2024-12-11 10:15:30', 'USER', false, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Jasmine', 'Frost', 'jasminefrost', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'jasminefrost@example.com', '2024-12-12 11:45:15', 'USER', true, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Kevin', 'Stone', 'kevinstone', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'kevinstone@example.com', '2024-12-13 14:20:45', 'ADMIN', true, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Laura', 'Hill', 'laurahill', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'laurahill@example.com', '2024-12-14 08:30:10', 'USER', false, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Michael', 'Ocean', 'michaelocean', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'michaelocean@example.com', '2024-12-15 09:50:00', 'USER', true, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Nina', 'Sky', 'ninasky', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'ninasky@example.com', '2024-12-16 16:10:25', 'USER', false, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Oscar', 'Wave', 'oscarwave', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'oscarwave@example.com', '2024-12-17 19:35:50', 'USER', true, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Paula', 'Cloud', 'paulacloud', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'paulacloud@example.com', '2024-12-18 07:25:40', 'USER', false, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Quentin', 'Rain', 'quentinrain', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'quentinrain@example.com', '2024-12-19 12:00:05', 'USER', true, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Rita', 'Storm', 'ritastorm', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'ritastorm@example.com', '2024-12-20 18:45:35', 'USER', true, 'NONE', true, '2017-10-01 21:58:58.508-07'),
('Sam', 'Thunder', 'samthunder', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'samthunder@example.com', '2024-12-21 10:05:15', 'ADMIN', false, 'NONE', true, '2017-10-01 21:58:58.508-07'),
('Tina', 'Lightning', 'tinalightning', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'tinalightning@example.com', '2024-12-22 14:55:20', 'ADMIN', true, 'SPONSORED_OSS', true, '2017-10-01 21:58:58.508-07'),
('Uma', 'Shadow', 'umashadow', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'umashadow@example.com', '2024-12-23 11:20:30', 'USER', true, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Victor', 'Flame', 'victorflame', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'victorflame@example.com', '2024-12-24 09:15:40', 'USER', false, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07'),
('Wendy', 'Frost', 'wendyfrost', '$2a$10$WaAe6aGfxIj/XLpd/4TsnOfWirzbSbb/2k5wfgjV5KgeJIxnOrzdS', 'wendyfrost@example.com', '2024-12-25 16:50:10', 'ADMIN', true, 'VERIFIED_PUBLISHER', true, '2017-10-01 21:58:58.508-07');


-- ROLES
INSERT INTO ROLE (name) VALUES ('ROLE_SUPER_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_USER');


-- USERS_ROLES
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (7, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (8, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (9, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (10, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (11, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (12, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (13, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (14, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (15, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (16, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (17, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (18, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (19, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (20, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (21, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (22, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (23, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (24, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (25, 2);


-- ORGANISATIONS
INSERT INTO organisations (name, description, owner_id, deactivated) VALUES
('Tech Innovators', 'A hub for cutting-edge technology solutions.', 1, false),
('Green Earth', 'Promoting eco-friendly practices worldwide.', 1, false),
('Health Matters', 'Innovative healthcare solutions for all.', 2, false),
('Code Creators', 'Building software for the modern age.', 3, false),
('EduFuture', 'Advancing education through technology.', 5, false),
('FinTech Pros', 'Financial solutions for the digital era.', 6, false),
('Urban Planners', 'Designing smart cities for tomorrow.', 7, false),
('Art Visionaries', 'Showcasing contemporary art globally.', 8, true),
('Sports Champs', 'Empowering young athletes worldwide.', 11, false),
('Food for All', 'Sustainable food distribution initiatives.', 11, true),
('Space Explorers', 'Exploring the final frontier.', 12, false),
('AI Thinkers', 'Researching and deploying AI technologies.', 12, false),
('Civic Builders', 'Community-driven urban development.', 13, true),
('Travel Gurus', 'Simplifying global travel experiences.', 14, false),
('Clean Energy', 'Developing renewable energy solutions.', 15, false),
('Wellness Co.', 'Innovative approaches to mental health.', 1, false),
('Game Makers', 'Creating immersive gaming experiences.', 1, false),
('Fashion Forward', 'Sustainable fashion for the future.', 2, true),
('LegalEase', 'Simplifying legal processes for businesses.', 2, false),
('Charity Connect', 'Linking donors with impactful causes.', 3, true);


-- TEAMS
INSERT INTO teams (name, description, team_permission, organisation_id) VALUES
-- Teams for "Tech Innovators"
('Development Team', 'Responsible for software development and maintenance.', 'READ_WRITE', 1),
('Research Team', 'Focuses on innovation and exploring new technologies.', 'READ_ONLY', 1),
('Admin Team', 'Manages organizational operations and permissions.', 'ADMIN', 1),

-- Teams for "Green Earth"
('Eco Research Team', 'Conducts research on sustainable practices.', 'READ_ONLY', 2),
('Campaign Team', 'Organizes eco-awareness campaigns.', 'READ_WRITE', 2),
('Green Admin Team', 'Manages overall organizational operations.', 'ADMIN', 2),

-- Teams for "Health Matters"
('Medical Innovation Team', 'Focuses on innovative healthcare solutions.', 'READ_WRITE', 3),
('Outreach Team', 'Handles healthcare campaigns and outreach.', 'READ_ONLY', 3),
('Health Admin Team', 'Oversees operations and resource allocation.', 'ADMIN', 3),

-- Teams for "Code Creators"
('Frontend Developers', 'Develops user-facing components of software.', 'READ_WRITE', 4),
('Backend Developers', 'Manages backend systems and APIs.', 'READ_WRITE', 4),
('DevOps Team', 'Ensures seamless deployment and maintenance.', 'ADMIN', 4),

-- Teams for "EduFuture"
('EdTech Developers', 'Creates educational technology products.', 'READ_WRITE', 5),
('Teacher Liaison Team', 'Connects with educators to gather feedback.', 'READ_ONLY', 5),
('Edu Admin Team', 'Oversees team coordination and permissions.', 'ADMIN', 5),

-- Teams for "FinTech Pros"
('Product Development Team', 'Develops financial products for clients.', 'READ_WRITE', 6),
('Risk Assessment Team', 'Analyzes financial risks and mitigations.', 'READ_ONLY', 6),
('FinTech Admin Team', 'Manages permissions and organizational resources.', 'ADMIN', 6),

-- Teams for "Urban Planners"
('City Design Team', 'Designs urban layouts and infrastructure.', 'READ_WRITE', 7),
('Sustainability Team', 'Ensures sustainable urban planning practices.', 'READ_ONLY', 7),
('Urban Admin Team', 'Manages overall operations and permissions.', 'ADMIN', 7),

-- Teams for "Art Visionaries"
('Curators', 'Curates contemporary art collections.', 'READ_WRITE', 8),
('Event Planners', 'Plans and organizes art exhibitions.', 'READ_ONLY', 8),
('Art Admin Team', 'Handles administrative tasks for events.', 'ADMIN', 8),

-- Teams for "Sports Champs"
('Athlete Support Team', 'Supports young athletes with training resources.', 'READ_ONLY', 9),
('Event Management Team', 'Organizes sports tournaments and events.', 'READ_WRITE', 9),
('Sports Admin Team', 'Oversees operations and logistics.', 'ADMIN', 9),

-- Teams for "Food for All"
('Distribution Team', 'Manages sustainable food distribution.', 'READ_WRITE', 10),
('Community Outreach Team', 'Collaborates with local communities.', 'READ_ONLY', 10),
('Food Admin Team', 'Supervises logistics and team operations.', 'ADMIN', 10),

-- Teams for "Space Explorers"
('Mission Control', 'Manages space exploration missions.', 'READ_WRITE', 11),
('Research Team of Space', 'Conducts research on space technologies.', 'READ_ONLY', 11),
('Space Admin Team', 'Handles administrative operations.', 'ADMIN', 11),

-- Teams for "AI Thinkers"
('AI Research Team', 'Explores AI algorithms and solutions.', 'READ_WRITE', 12),
('Data Collection Team', 'Prepares datasets for AI research.', 'READ_ONLY', 12),
('AI Admin Team', 'Supervises project coordination.', 'ADMIN', 12),

-- Teams for "Civic Builders"
('Urban Development Team', 'Manages urban construction projects.', 'READ_WRITE', 13),
('Community Relations Team', 'Engages with local communities for feedback.', 'READ_ONLY', 13),
('Civic Admin Team', 'Handles organizational operations and strategy.', 'ADMIN', 13),

-- Teams for "Travel Gurus"
('Customer Support Team', 'Provides support to travelers globally.', 'READ_ONLY', 14),
('Product Development Team of Travel', 'Builds tools for seamless travel experiences.', 'READ_WRITE', 14),
('Travel Admin Team', 'Oversees travel operations and management.', 'ADMIN', 14);



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


--REPOS WITHOUT ORGANISATION 7, 14, 19, 29, 37, 39 = 6


-- REPOSITORIES
INSERT INTO repositories (name, namespace, description, visibility, created, updated, star, personal, category, owner_id, type, prefix, badge, organisation_id, deleted) VALUES
('WeatherAPI', 'api-tools', 'Weather forecasting API tool', 'PUBLIC', '2024-12-01 10:00:00', '2024-12-01 12:00:00', 85, true, 'API_MANAGMENT', 1, 'Repository', NULL, 'NONE', 1, false),
('CMSBuilder', 'content-dev', 'Content management system for blogs', 'PUBLIC', '2024-12-02 14:00:00', '2024-12-02 15:30:00', 42, false, 'CONTENT_MANAGMENT_SYSTEM', 2, 'Official', 'OFF-001', 'DOCKER_OFFICIAL_IMAGE', 2, false),
('DataAnalyzer', 'data-tools', 'Data science library for analytics', 'PUBLIC', '2024-12-03 08:30:00', '2024-12-03 09:45:00', 110, true, 'DATA_SCIENCE', 3, 'Repository', NULL, 'NONE', 3, false),
('SecureStorage', 'db-storage', 'Secure database storage solutions', 'PUBLIC', '2024-12-04 09:00:00', '2024-12-04 11:20:00', 78, false, 'DATABASE_AND_STORAGE', 5, 'Official', 'OFF-002', 'DOCKER_OFFICIAL_IMAGE', 4, false),
('PythonFramework', 'lang-lib', 'Python framework for web apps', 'PUBLIC', '2024-12-05 13:45:00', '2024-12-05 14:15:00', 200, true, 'LANGUAGE_AND_FRAMEWORKS', 5, 'Repository', NULL, 'NONE', 4, false),
('IoTPlatform', 'iot-dev', 'IoT platform for smart devices', 'PRIVATE', '2024-12-06 07:00:00', '2024-12-06 08:10:00', 54, false, 'INTERNET_OF_THINGS', 6, 'Official', 'OFF-003', 'DOCKER_OFFICIAL_IMAGE', 5, false),
('MLToolkit', 'ai-tools', 'Machine learning toolkit for developers', 'PUBLIC', '2024-12-07 09:30:00', '2024-12-07 10:45:00', 130, true, 'MACHINE_LEARNING_AND_AI', 7, 'Repository', NULL, 'NONE', null, false),
('CloudStorage', 'cloud-solutions', 'Cloud storage solutions for enterprises', 'PUBLIC', '2024-12-08 12:15:00', '2024-12-08 14:00:00', 95, false, 'DATABASE_AND_STORAGE', 8, 'Official', 'OFF-004', 'DOCKER_OFFICIAL_IMAGE', 6, false),
('DevOpsPipeline', 'devops-tools', 'Continuous integration and deployment pipeline', 'PUBLIC', '2024-12-09 15:00:00', '2024-12-09 16:30:00', 88, true, 'DEVOPS', 8, 'Repository', NULL, 'NONE', 6, false),
('WebServerFramework', 'web-server', 'Web server framework for fast deployments', 'PUBLIC', '2024-12-10 11:30:00', '2024-12-10 13:00:00', 210, true, 'WEB_SERVERS', 8, 'Official', 'OFF-005', 'DOCKER_OFFICIAL_IMAGE', 1, false),
('DataPipeline', 'data-processing', 'Data pipeline tools for processing large datasets', 'PUBLIC', '2024-12-11 14:30:00', '2024-12-11 15:45:00', 115, false, 'INTEGRATION_AND_DELIVERY', 11, 'Repository', NULL, 'NONE', 1, false),
('SecurityToolkit', 'security-tools', 'Security tools for system administrators', 'PRIVATE', '2024-12-12 16:45:00', '2024-12-12 18:00:00', 67, false, 'SECURITY', 12, 'Official', 'OFF-006', 'DOCKER_OFFICIAL_IMAGE', 2, false),
('MessagingQueue', 'message-queues', 'Message queues for microservice communication', 'PUBLIC', '2024-12-13 10:00:00', '2024-12-13 11:20:00', 150, true, 'MESSAGE_QUEUES', 13, 'Repository', NULL, 'NONE', 3, false),
('MentoringPlatform', 'mentoring-tools', 'Online mentoring platform for developers', 'PUBLIC', '2024-12-14 13:30:00', '2024-12-14 14:40:00', 102, false, 'MENTORING_AND_OBSERVABILITY', 14, 'Official', 'OFF-007', 'DOCKER_OFFICIAL_IMAGE', null, false),
('NetworkingTools', 'networking-dev', 'Networking tools for large-scale networks', 'PUBLIC', '2024-12-15 09:45:00', '2024-12-15 11:00:00', 80, true, 'NETWORKING', 15, 'Repository', NULL, 'NONE', 6, false),
('OSLibrary', 'os-tools', 'Operating system utilities for developers', 'PRIVATE', '2024-12-16 14:00:00', '2024-12-16 15:10:00', 50, false, 'OPERATING_SYSTEM', 16, 'Official', 'OFF-008', 'DOCKER_OFFICIAL_IMAGE', 8, false),
('ObservabilityTools', 'observability', 'Tools for monitoring and observability', 'PUBLIC', '2024-12-17 12:30:00', '2024-12-17 14:10:00', 120, true, 'OBSERVABILITY', 17, 'Repository', NULL, 'NONE', 9, false),
('SecureAPI', 'api-security', 'Secure API authentication and authorization', 'PRIVATE', '2024-12-18 08:15:00', '2024-12-18 09:30:00', 95, false, 'SECURITY', 18, 'Official', 'OFF-009', 'DOCKER_OFFICIAL_IMAGE', 10, false),
('DockerExtension', 'docker-tools', 'Extensions for enhancing Docker workflows', 'PUBLIC', '2024-12-19 11:00:00', '2024-12-19 12:45:00', 130, true, 'DOCKER_EXTENSIONS', 18, 'Repository', NULL, 'NONE', 11, false),
('CloudAPI', 'cloud-tools', 'API for integrating with cloud services', 'PUBLIC', '2024-12-20 09:30:00', '2024-12-20 10:45:00', 200, true, 'API_MANAGMENT', 18, 'Official', 'OFF-010', 'DOCKER_OFFICIAL_IMAGE', 12, false),
('FrontendFramework', 'web-dev', 'Frontend framework for modern web apps', 'PUBLIC', '2024-12-21 14:00:00', '2024-12-21 15:30:00', 180, true, 'FRONTEND', 21, 'Repository', NULL, 'NONE', 13, false),
('PythonTools', 'python-utils', 'Tools for Python developers', 'PUBLIC', '2024-12-22 13:30:00', '2024-12-22 14:55:00', 110, false, 'LANGUAGE_AND_FRAMEWORKS', 22, 'Official', 'OFF-011', 'DOCKER_OFFICIAL_IMAGE', 14, false),
('JavaFramework', 'java-lib', 'Java library for backend apps', 'PUBLIC', '2024-12-23 12:00:00', '2024-12-23 13:25:00', 95, true, 'LANGUAGE_AND_FRAMEWORKS', 23, 'Repository', NULL, 'NONE', 15, false),
('DatabaseTools', 'db-dev', 'Tools for managing databases', 'PUBLIC', '2024-12-24 09:45:00', '2024-12-24 11:10:00', 130, false, 'DATABASE_AND_STORAGE', 24, 'Official', 'OFF-012', 'DOCKER_OFFICIAL_IMAGE', 16, false),
('AIPlatform', 'ai-development', 'Platform for building AI models', 'PUBLIC', '2024-12-25 10:00:00', '2024-12-25 11:30:00', 150, true, 'MACHINE_LEARNING_AND_AI', 25, 'Repository', NULL, 'NONE', 17, false),
('BackupTools', 'backup-dev', 'Tools for automated backups', 'PRIVATE', '2024-12-26 08:15:00', '2024-12-26 09:40:00', 50, false, 'DATABASE_AND_STORAGE', 1, 'Official', 'OFF-013', 'DOCKER_OFFICIAL_IMAGE', 18, false),
('RealTimeAnalytics', 'realtime-data', 'Real-time data processing and analytics', 'PUBLIC', '2024-12-27 14:30:00', '2024-12-27 15:55:00', 120, true, 'DATA_SCIENCE', 1, 'Repository', NULL, 'NONE', 19, false),
('WebCrawler', 'web-tools', 'Web crawler for data scraping', 'PUBLIC', '2024-12-28 11:00:00', '2024-12-28 12:15:00', 70, false, 'DEVOPS', 2, 'Official', 'OFF-014', 'DOCKER_OFFICIAL_IMAGE', 20, false),
('DatabaseBackup', 'db-tools', 'Backup solutions for databases', 'PUBLIC', '2024-12-29 09:30:00', '2024-12-29 11:05:00', 65, true, 'DATABASE_AND_STORAGE', 2, 'Repository', NULL, 'NONE', null, false),
('VirtualizationTools', 'virtualization-dev', 'Tools for virtualized environments', 'PUBLIC', '2024-12-30 10:00:00', '2024-12-30 11:25:00', 90, false, 'DATABASE_AND_STORAGE', 3, 'Official', 'OFF-015', 'DOCKER_OFFICIAL_IMAGE', null, false),
('WebSocketServer', 'web-server', 'WebSocket server implementation for apps', 'PUBLIC', '2024-12-31 13:30:00', '2024-12-31 14:40:00', 150, true, 'WEB_SERVERS',3, 'Repository', NULL, 'NONE', 10, false),
('AuthenticationService', 'auth-service', 'Service for handling user authentication', 'PRIVATE', '2024-11-01 08:45:00', '2024-11-01 10:05:00', 55, false, 'SECURITY', 3, 'Official', 'OFF-016', 'DOCKER_OFFICIAL_IMAGE', 9, false),
('ServerManagement', 'server-admin', 'Tools for server management and monitoring', 'PUBLIC', '2024-11-02 10:15:00', '2024-11-02 11:25:00', 100, true, 'SECURITY', 5, 'Repository', NULL, 'NONE', 8, false),
('DataWarehouse', 'big-data', 'Warehouse for storing large datasets', 'PRIVATE', '2024-11-03 09:00:00', '2024-11-03 10:30:00', 130, false, 'DATA_SCIENCE', 5, 'Official', 'OFF-017', 'DOCKER_OFFICIAL_IMAGE', 7, false),
('FileCompression', 'file-utils', 'Tools for compressing files and archives', 'PUBLIC', '2024-11-04 10:40:00', '2024-11-04 12:10:00', 40, true, 'OPERATING_SYSTEM', 6, 'Repository', NULL, 'NONE', 6, true),
('TaskScheduler', 'task-management', 'Task scheduling and automation system', 'PUBLIC', '2024-11-05 08:00:00', '2024-11-05 09:15:00', 98, false, 'OPERATING_SYSTEM', 6, 'Official', 'OFF-018', 'DOCKER_OFFICIAL_IMAGE', 5, true),
('APIIntegration', 'api-tools', 'API integration for external systems', 'PUBLIC', '2024-11-06 13:30:00', '2024-11-06 14:45:00', 160, true, 'API_MANAGMENT', 7, 'Repository', NULL, 'NONE', null, true),
('TestAutomation', 'automation-tools', 'Automated testing tools for apps', 'PUBLIC', '2024-11-07 09:45:00', '2024-11-07 11:00:00', 120, false, 'OPERATING_SYSTEM', 7, 'Official', 'OFF-019', 'DOCKER_OFFICIAL_IMAGE', null, true),
('SystemMonitoring', 'system-admin', 'Tools for system and performance monitoring', 'PUBLIC', '2024-11-08 08:10:00', '2024-11-08 09:20:00', 80, true, 'OPERATING_SYSTEM', 8, 'Repository', NULL, 'NONE', null, true),
('APIProxy', 'api-gateway', 'API gateway for routing requests', 'PUBLIC', '2024-11-09 10:30:00', '2024-11-09 11:40:00', 70, false, 'API_MANAGMENT', 8, 'Official', 'OFF-020', 'DOCKER_OFFICIAL_IMAGE', null, true);


-- TAGS
INSERT INTO tags (name, docker_pull_command, repository_id, pulled, pushed, author, deleted) VALUES
('weather-api', 'docker pull api-tools/weather-api', 1, '1 day', '2 days', 'johndoe', false),
('forecasting', 'docker pull api-tools/forecasting', 1, '3 days', 'about a week', 'janesmith', false),
('weather', 'docker pull api-tools/weather', 1, '2 days', '3 day', 'alicebrown', false),
('api-tool', 'docker pull api-tools/api-tool', 1, '3 days', '5 days', 'bobwhite', false),
('climate', 'docker pull api-tools/climate', 1, 'about a week', 'about a month', 'charliegreen', false),
('cms-builder', 'docker pull content-dev/cms-builder', 2, '1 day', '5 days', 'dianablue', false),
('blog-cms', 'docker pull content-dev/blog-cms', 2, '2 days', 'about a week', 'ethanblack', false),
('content-management', 'docker pull content-dev/content-management', 2, 'about a month', 'about a month', 'fionagray', false),
('cms-core', 'docker pull content-dev/cms-core', 2, '3 days', '5 day', 'georgeyellow', false),
('private-cms', 'docker pull content-dev/private-cms', 2, '2 days', '3 days', 'hannahred', false),
('blogging-platform', 'docker pull content-dev/blogging-platform', 2, 'about a week', 'about a month', 'ivancarter', false),
('content-dev-tool', 'docker pull content-dev/content-dev-tool', 2, '1 day', '2 days', 'jasminefrost', false),
('data-analyzer', 'docker pull data-tools/data-analyzer', 3, '2 days', '5 days', 'kevinstone', false),
('analytics', 'docker pull data-tools/analytics', 3, '3 days', 'about a week', 'laurahill', false),
('data-science', 'docker pull data-tools/data-science', 3, 'about a month', 'about a month', 'michaelocean', false),
('data-tools', 'docker pull data-tools/data-tools', 3, '1 day', '2 days', 'ninasky', false),
('machine-learning', 'docker pull data-tools/machine-learning', 3, 'about a week', 'about a month', 'oscarwave', false),
('ai-analytics', 'docker pull data-tools/ai-analytics', 3, '1 day', 'about a month', 'paulacloud', false),
('big-data', 'docker pull data-tools/big-data', 3, '2 days', '5 days', 'quentinrain', false),
('secure-storage', 'docker pull db-storage/secure-storage', 4, '3 days', 'about a week', 'ritastorm', false),
('database-security', 'docker pull db-storage/database-security', 4, '1 day', '1 day', 'samthunder', false),
('encrypted-db', 'docker pull db-storage/encrypted-db', 4, '2 days', '5 days', 'tinalightning', false),
('secure-db', 'docker pull db-storage/secure-db', 4, 'about a week', 'about a week', 'umashadow', false),
('storage-solutions', 'docker pull db-storage/storage-solutions', 4, '1 day', 'about a month', 'victorflame', false),
('private-db', 'docker pull db-storage/private-db', 4, '2 days', '5 days', 'wendyfrost', false),
('safe-storage', 'docker pull db-storage/safe-storage', 4, '3 days', 'about a week', 'johndoe', false),
('python-framework', 'docker pull lang-lib/python-framework', 5, '1 day', '1 day', 'janesmith', false),
('web-framework', 'docker pull lang-lib/web-framework', 5, '2 days', '5 days', 'alicebrown', false),
('python-web', 'docker pull lang-lib/python-web', 5, 'about a week', '3 days', 'bobwhite', false),
('backend-framework', 'docker pull lang-lib/backend-framework', 5, '1 day', 'about a month', 'charliegreen', false),
('language-library', 'docker pull lang-lib/language-library', 5, '2 days', '5 days', 'dianablue', false),
('fast-python', 'docker pull lang-lib/fast-python', 5, '3 days', 'about a week', 'ethanblack', false),
('framework-tools', 'docker pull lang-lib/framework-tools', 5, 'about a month', 'about a month', 'fionagray', false),
('iot-platform', 'docker pull iot-dev/iot-platform', 6, '3 days', '5 days', 'georgeyellow', false),
('smart-devices', 'docker pull iot-dev/smart-devices', 6, 'about a week', 'about a week', 'hannahred', false),
('iot-dev', 'docker pull iot-dev/iot-dev', 6, '1 day', 'about a month', 'ivancarter', false),
('device-management', 'docker pull iot-dev/device-management', 6, '2 days', '5 days', 'jasminefrost', false),
('iot-solutions', 'docker pull iot-dev/iot-solutions', 6, '3 days', 'about a week', 'kevinstone', false),
('connected-devices', 'docker pull iot-dev/connected-devices', 6, 'about a month', 'about a month', 'laurahill', false),
('iot-tools', 'docker pull iot-dev/iot-tools', 6, '2 days', '5 days', 'michaelocean', false),
('ml-toolkit', 'docker pull ai-tools/ml-toolkit', 7, 'about a week', 'about a week', 'ninasky', false),
('ai-development', 'docker pull ai-tools/ai-development', 7, '1 day', 'about a month', 'oscarwave', false),
('ml-library', 'docker pull ai-tools/ml-library', 7, '2 days', '5 days', 'paulacloud', false),
('ai-tools', 'docker pull ai-tools/ai-tools', 7, '3 days', 'about a week', 'quentinrain', false),
('deep-learning', 'docker pull ai-tools/deep-learning', 7, '1 day', '1 day', 'ritastorm', false),
('ai-solutions', 'docker pull ai-tools/ai-solutions', 7, '2 days', '5 days', 'samthunder', false),
('ml-development', 'docker pull ai-tools/ml-development', 7, '3 days', 'about a week', 'tinalightning', false),
('cloud-storage', 'docker pull cloud-solutions/cloud-storage', 8, '1 day', 'about a month', 'umashadow', false),
('enterprise-storage', 'docker pull cloud-solutions/enterprise-storage', 8, '2 days', '5 days', 'victorflame', false),
('db-storage', 'docker pull cloud-solutions/db-storage', 8, '3 days', 'about a week', 'wendyfrost', false),
('secure-cloud', 'docker pull cloud-solutions/secure-cloud', 8, '1 day', '1 day', 'johndoe', false),
('cloud-solutions', 'docker pull cloud-solutions/cloud-solutions', 8, '2 days', '3 days', 'janesmith', false),
('data-storage', 'docker pull cloud-solutions/data-storage', 8, '2 days', '3 days', 'alicebrown', false),
('backup-solutions', 'docker pull cloud-solutions/backup-solutions', 8, '1 day', 'about a month', 'bobwhite', false),
('devops-pipeline', 'docker pull devops-tools/devops-pipeline', 9, '2 days', '5 days', 'charliegreen', false),
('ci-cd', 'docker pull devops-tools/ci-cd', 9, '3 days', 'about a week', 'dianablue', false),
('automation-tools', 'docker pull devops-tools/automation-tools', 9, '3 days', 'about a week', 'ethanblack', false),
('deployment-pipeline', 'docker pull devops-tools/deployment-pipeline', 9, '2 days', '5 days', 'fionagray', false),
('devops-tools', 'docker pull devops-tools/devops-tools', 9, '2 days', '5 days', 'georgeyellow', false),
('continuous-delivery', 'docker pull devops-tools/continuous-delivery', 9, '1 day', 'about a month', 'hannahred', false),
('integration-tools', 'docker pull devops-tools/integration-tools', 9, '2 days', '5 days', 'ivancarter', false),
('web-server-framework', 'docker pull web-server/web-server-framework', 10, '3 days', 'about a week', 'jasminefrost', false),
('web-server', 'docker pull web-server/web-server', 10, '2 days', '5 days', 'kevinstone', false),
('deployment-tools', 'docker pull web-server/deployment-tools', 10, '2 days', '3 days', 'laurahill', false),
('fast-deployment', 'docker pull web-server/fast-deployment', 10, '3 days', 'about a week', 'michaelocean', false),
('server-framework', 'docker pull web-server/server-framework', 10, '1 day', 'about a month', 'ninasky', false),
('scalable-server', 'docker pull web-server/scalable-server', 10, '2 days', '5 days', 'oscarwave', false),
('web-app-tools', 'docker pull web-server/web-app-tools', 10, '3 days', 'about a week', 'paulacloud', false),
('data-pipeline', 'docker pull data-processing/data-pipeline', 11, '1 day', 'about a month', 'quentinrain', false),
('large-dataset-tools', 'docker pull data-processing/large-dataset-tools', 11, '2 days', '2 days', 'ritastorm', false),
('data-processing', 'docker pull data-processing/data-processing', 11, '3 days', 'about a week', 'samthunder', false),
('security-toolkit', 'docker pull security-tools/security-toolkit', 12, '1 day', 'about a month', 'tinalightning', false),
('sysadmin-tools', 'docker pull security-tools/sysadmin-tools', 12, '2 days', '5 days', 'umashadow', false),
('system-security', 'docker pull security-tools/system-security', 12, '3 days', 'about a week', 'victorflame', false),
('messaging-queue', 'docker pull message-queues/messaging-queue', 13, '1 day', 'about a month', 'wendyfrost', false),
('microservices-communication', 'docker pull message-queues/microservices-communication', 13, '2 days', '2 days', 'johndoe', false),
('message-queues', 'docker pull message-queues/message-queues', 13, '3 days', 'about a week', 'janesmith', false),
('mentoring-platform', 'docker pull mentoring-tools/mentoring-platform', 14, '1 day', 'about a month', 'alicebrown', false),
('developer-tools2', 'docker pull mentoring-tools/developer-tools', 14, '2 days', '5 days', 'bobwhite', false),
('mentoring-tools', 'docker pull mentoring-tools/mentoring-tools', 14, '3 days', 'about a week', 'charliegreen', false),
('networking-tools', 'docker pull networking-dev/networking-tools', 15, '3 days', 'about a month', 'dianablue', false),
('large-scale-networks', 'docker pull networking-dev/large-scale-networks', 15, '5 days', '5 days', 'ethanblack', false),
('networking-dev', 'docker pull networking-dev/networking-dev', 15, '3 days', 'about a week', 'fionagray', false),
('os-library', 'docker pull os-tools/os-library', 16, '1 day', 'about a month', 'georgeyellow', false),
('os-utilities', 'docker pull os-tools/os-utilities', 16, '2 days', '5 days', 'hannahred', false),
('os-tools', 'docker pull os-tools/os-tools', 16, '3 days', 'about a week', 'ivancarter', false),
('observability-tools', 'docker pull observability/observability-tools', 17, '3 days', 'about a month', 'jasminefrost', false),
('monitoring-tools', 'docker pull observability/monitoring-tools', 17, '2 days', '2 days', 'kevinstone', false),
('observability', 'docker pull observability/observability', 17, '3 days', 'about a week', 'laurahill', false),
('secure-api', 'docker pull api-security/secure-api', 18, '1 day', 'about a month', 'michaelocean', false),
('api-authentication', 'docker pull api-security/api-authentication', 18, '2 days', '5 days', 'ninasky', false),
('api-security', 'docker pull api-security/api-security', 18, '3 days', 'about a week', 'oscarwave', false),
('docker-extension', 'docker pull docker-tools/docker-extension', 19, '1 day', 'about a month', 'paulacloud', false),
('docker-enhancements', 'docker pull docker-tools/docker-enhancements', 19, '2 days', '2 days', 'quentinrain', false),
('docker-tools', 'docker pull docker-tools/docker-tools', 19, '3 days', 'about a week', 'ritastorm', false),
('cloud-api', 'docker pull cloud-tools/cloud-api', 20, '1 day', 'about a month', 'samthunder', false),
('cloud-integration', 'docker pull cloud-tools/cloud-integration', 20, '2 days', '5 days', 'tinalightning', false),
('cloud-tools', 'docker pull cloud-tools/cloud-tools', 20, '3 days', 'about a week', 'umashadow', false),
('modern-web-apps', 'docker pull web-dev/modern-web-apps', 21, 'about a week', 'about a month', 'victorflame', false),
('frontend-framework', 'docker pull web-dev/frontend-framework', 21, '1 day', '2 days', 'wendyfrost', false),
('python-tools', 'docker pull python-utils/python-tools', 22, 'about a week', 'about a week', 'johndoe', false),
('developer-tools3', 'docker pull python-utils/developer-tools3', 22, '1 day', 'about a month', 'janesmith', false),
('java-library', 'docker pull java-lib/java-library', 23, '2 days', '5 days', 'alicebrown', false),
('backend-tools', 'docker pull java-lib/backend-tools', 23, '3 days', 'about a week', 'bobwhite', false),
('db-tools', 'docker pull db-dev/db-tools', 24, 'about a week', 'about a month', 'charliegreen', false),
('database-management', 'docker pull db-dev/database-management', 24, '1 day', '2 days', 'dianablue', false),
('ai-platform', 'docker pull ai-development/ai-platform', 25, 'about a week', 'about a week', 'ethanblack', false),
('ml-development2', 'docker pull ai-development/ml-development2', 25, '1 day', 'about a month', 'fionagray', false),
('automated-backups', 'docker pull backup-dev/automated-backups', 26, '2 days', '5 days', 'georgeyellow', false),
('backup-tools', 'docker pull backup-dev/backup-tools', 26, '3 days', 'about a week', 'hannahred', false),
('real-time-data', 'docker pull realtime-data/real-time-data', 27, 'about a month', 'about a month', 'ivancarter', false),
('data-analytics', 'docker pull realtime-data/data-analytics', 27, '2 days', '5 days', 'jasminefrost', false),
('web-crawling', 'docker pull web-tools/web-crawling', 28, 'about a week', 'about a month', 'kevinstone', false),
('data-scraping', 'docker pull web-tools/data-scraping', 28, '1 day', 'about a month', 'laurahill', false),
('database-backups', 'docker pull db-tools/database-backups', 29, '2 days', '5 days', 'michaelocean', false),
('backup-solutions2', 'docker pull db-tools/backup-solutions2', 29, '3 days', 'about a week', 'ninasky', false),
('virtualization-tools', 'docker pull virtualization-dev/virtualization-tools', 30, 'about a month', 'about a month', 'oscarwave', false),
('virtualized-environments', 'docker pull virtualization-dev/virtualized-environments', 30, '1 day', '2 days', 'paulacloud', false),
('websockets2', 'docker pull web-server/websockets2', 31, 'about a week', 'about a month', 'quentinrain', false),
('web-server-framework2', 'docker pull web-server/web-server-framework2', 31, '1 day', 'about a month', 'ritastorm', false),
('auth-service', 'docker pull auth-service/auth-service', 32, '2 days', '5 days', 'samthunder', false),
('user-authentication', 'docker pull auth-service/user-authentication', 32, '3 days', 'about a week', 'tinalightning', false),
('server-management', 'docker pull server-admin/server-management', 33, 'about a month', 'about a month', 'umashadow', false),
('monitoring-tools2', 'docker pull server-admin/monitoring-tools2', 33, '1 day', '3 days', 'victorflame', false),
('data-warehouse', 'docker pull big-data/data-warehouse', 34, '3 days', 'about a week', 'wendyfrost', false),
('big-data-tools', 'docker pull big-data/big-data-tools', 34, '1 day', 'about a month', 'johndoe', false),
('file-compression', 'docker pull file-utils/file-compression', 35, '2 days', '5 days', 'janesmith', false),
('archive-tools', 'docker pull file-utils/archive-tools', 35, '3 days', 'about a week', 'alicebrown', false),
('task-scheduler', 'docker pull task-management/task-scheduler', 36, 'about a month', 'about a month', 'bobwhite', false),
('automation-tools4', 'docker pull task-management/automation-tools4', 36, '1 day', '2 days', 'charliegreen', false),
('api-integration', 'docker pull api-tools/api-integration', 37, 'about a month', 'about a month', 'dianablue', false),
('external-systems', 'docker pull api-tools/external-systems', 37, '1 day', 'about a month', 'ethanblack', false),
('test-automation', 'docker pull automation-tools/test-automation', 38, '2 days', '5 days', 'fionagray', false),
('quality-assurance', 'docker pull automation-tools/quality-assurance', 38, '3 days', 'about a week', 'georgeyellow', false),
('system-monitoring', 'docker pull system-admin/system-monitoring', 39, 'about a month', 'about a month', 'hannahred', false),
('performance-tools', 'docker pull system-admin/performance-tools', 39, '2 days', '2 days', 'ivancarter', false),
('api-proxy', 'docker pull api-gateway/api-proxy', 40, 'about a month', 'about a month', 'jasminefrost', false),
('request-routing', 'docker pull api-gateway/request-routing', 40, '1 day', 'about a month', 'kevinstone', false);

--STARS
INSERT INTO starred_repositories (user_id, repository_id, starred_at) VALUES
(1, 7, '2025-01-15 10:30:00'),
(2, 7, '2025-01-16 14:22:15'),
(3, 7, '2025-01-18 09:45:30'),
(4, 7, '2025-01-20 16:12:45'),
(1, 14, '2025-02-08 17:25:40'),
(1, 14, '2025-02-10 10:55:15'),
(1, 40, '2025-02-12 14:30:30');

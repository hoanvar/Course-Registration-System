const jwt = require('jsonwebtoken');
const config = require('../config/config');

const authController = {
    login: async (req, res) => {
        try {
            const { username, password } = req.body;

            // TODO: Thêm logic xác thực người dùng ở đây
            if (username === "admin" && password === "password") {
                const token = jwt.sign(
                    {
                        sub: "1234567890",
                        name: username,
                        admin: true
                    },
                    config.jwtSecret,
                    { expiresIn: config.jwtExpiresIn }
                );

                return res.json({
                    success: true,
                    token,
                    user: {
                        username,
                        role: "admin"
                    }
                });
            }

            return res.status(401).json({
                success: false,
                message: "Invalid credentials"
            });
        } catch (error) {
            return res.status(500).json({
                success: false,
                message: "Internal server error"
            });
        }
    },

    validateToken: async (req, res) => {
        try {
            const token = req.headers.authorization?.split(' ')[1];

            if (!token) {
                return res.status(401).json({
                    success: false,
                    message: "No token provided"
                });
            }

            const decoded = jwt.verify(token, config.jwtSecret);
            return res.json({
                success: true,
                user: decoded
            });
        } catch (error) {
            return res.status(401).json({
                success: false,
                message: "Invalid token"
            });
        }
    }
};

module.exports = authController;
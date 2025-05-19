const jwt = require('jsonwebtoken');
const config = require('../config/config');

const authMiddleware = {
    verifyToken: (req, res, next) => {
        try {
            const token = req.headers.authorization?.split(' ')[1];

            if (!token) {
                return res.status(401).json({
                    success: false,
                    message: "No token provided"
                });
            }

            const decoded = jwt.verify(token, config.jwtSecret);
            req.user = decoded;
            next();
        } catch (error) {
            return res.status(401).json({
                success: false,
                message: "Invalid token"
            });
        }
    }
};

module.exports = authMiddleware;
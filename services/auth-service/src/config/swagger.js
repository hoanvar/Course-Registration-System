const swaggerJsdoc = require('swagger-jsdoc');
const { loginSchema, loginResponseSchema, validateResponseSchema } = require('../schemas/auth.schema');

const options = {
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'Auth Service API',
            version: '1.0.0',
            description: 'API documentation for Authentication Service'
        },
        servers: [
            {
                url: 'http://localhost:4004',
                description: 'Development server'
            }
        ],
        components: {
            securitySchemes: {
                bearerAuth: {
                    type: 'http',
                    scheme: 'bearer',
                    bearerFormat: 'JWT'
                }
            },
            schemas: {
                loginSchema,
                loginResponseSchema,
                validateResponseSchema
            }
        }
    },
    apis: ['./src/routes/*.js']
};

module.exports = swaggerJsdoc(options); 
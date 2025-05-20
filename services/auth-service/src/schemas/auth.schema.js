const loginSchema = {
    type: 'object',
    required: ['username', 'password'],
    properties: {
        username: { type: 'string', example: 'admin' },
        password: { type: 'string', example: 'password' }
    }
};

const loginResponseSchema = {
    type: 'object',
    properties: {
        success: { type: 'boolean', example: true },
        token: { type: 'string', example: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...' },
        user: {
            type: 'object',
            properties: {
                username: { type: 'string', example: 'admin' },
                role: { type: 'string', example: 'admin' }
            }
        }
    }
};

const validateResponseSchema = {
    type: 'object',
    properties: {
        success: { type: 'boolean', example: true },
        user: {
            type: 'object',
            properties: {
                sub: { type: 'string', example: '1234567890' },
                name: { type: 'string', example: 'admin' },
                admin: { type: 'boolean', example: true }
            }
        }
    }
};

module.exports = {
    loginSchema,
    loginResponseSchema,
    validateResponseSchema
}; 
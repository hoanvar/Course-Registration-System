const express = require('express');
const cors = require('cors');
const config = require('./config/config');
const authRoutes = require('./routes/authRoutes');
const eurekaClient = require('./config/eureka');

const app = express();

app.use(cors());
app.use(express.json());

// Health check endpoint
app.get('/health', (req, res) => {
    res.status(200).json({
        status: 'OK',
        service: 'auth-service',
        timestamp: new Date().toISOString()
    });
});

// Routes
app.use('/auth', authRoutes);

// Start server
const server = app.listen(config.port, () => {
    console.log(`Auth service is running on port ${config.port}`);
});

// Register with Eureka with retry
let retryCount = 0;
const maxRetries = 5;
const retryInterval = 5000;

function registerWithEureka() {
    eurekaClient.start(error => {
        if (error) {
            console.error(`Error registering with Eureka (attempt ${retryCount + 1}/${maxRetries}):`, error);
            if (retryCount < maxRetries) {
                retryCount++;
                console.log(`Retrying in ${retryInterval / 1000} seconds...`);
                setTimeout(registerWithEureka, retryInterval);
            } else {
                console.error('Max retries reached. Could not register with Eureka.');
            }
        } else {
            console.log('Successfully registered with Eureka');
        }
    });
}

// Start registration process
registerWithEureka();

// Handle graceful shutdown
process.on('SIGTERM', () => {
    console.log('SIGTERM received. Shutting down gracefully...');
    eurekaClient.stop();
    server.close(() => {
        console.log('Server closed');
        process.exit(0);
    });
});
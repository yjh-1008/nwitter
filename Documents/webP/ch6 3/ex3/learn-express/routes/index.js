/**
 * ch6/ex3/learn-express/routes/index.js
 */

const express = require('express');

const router = express.Router();

// GET / 라우터
router.get('/', (req, res) => {
  res.send('Hello, Express');
});

module.exports = router;

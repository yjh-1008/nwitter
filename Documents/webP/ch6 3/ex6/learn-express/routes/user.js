/**
 * ch6/ex6/learn-express/routes/user.js
 */

const express = require('express');

const router = express.Router();

// GET /user 라우터
router.get('/', (req, res) => {
  res.send('Hello, User');
});

module.exports = router;

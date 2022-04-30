/**
 * ch6/ex6/learn-express/routes/index.js
 */

const express = require('express');

const router = express.Router();

// GET / 라우터
router.get('/', (req, res) => {
  res.render('index', { title: 'Express' });
});

module.exports = router;

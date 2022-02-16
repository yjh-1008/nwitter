const express = require('express');
const app = express();
const mongoose = require('mongoose');
mongoose.connect('mongodb+srv://react:wh2014@cluster0.jgbrq.mongodb.net/myFirstDatabase?retryWrites=true&w=majority')
    .then(()=>console.log('Mongodb Connected'))
    .catch('Error');
const port=3000;

app.get('/', (req,res)=>res.send('Hello world'));
//mongodb+srv://react:<password>@cluster0.jgbrq.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
app.listen(port, ()=> console.log(`Example  app listening on port ${port}!`));
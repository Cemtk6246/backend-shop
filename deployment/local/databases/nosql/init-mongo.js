db = db.getSiblingDB('products');

db.createUser({
  user: 'products',
  pwd: 'products',
  roles: [
    { role: 'readWrite', db: 'products' }
  ]
});

db.myCollection.insertOne({ initializedAt : new Date() });
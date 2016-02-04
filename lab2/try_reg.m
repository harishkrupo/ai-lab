close all
clear all

train_data=csvread('Iris_data_norm_train.txt');
test_data=csvread('iris_data_norm_test.txt');

function w=regre(X,Y)

    w = inv(X'*X)*X'*Y;
end

Xtrainwo=[ones(1,size(train_data)(1))' train_data(:,1:4)];
Xtestwo=[ones(1,size(test_data)(1))' test_data(:,1:4)];

out=regre(Xtrainwo,train_data(:,5));
Xtrainout=Xtrainwo*out;

Xtestout=Xtestwo*out;

miscount = sum( xor( Xtrainout<0, train_data(:,5)<0 ));
Ein= miscount/110;
miscount = sum(xor( Xtestout<0, test_data(:,5)<0 ));
Eout= miscount/41;

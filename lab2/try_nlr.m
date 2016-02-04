close all
clear all

train_data=csvread('Iris_data_norm_train.txt');
test_data=csvread('iris_data_norm_test.txt');

function w=regre(X,Y)

    w = inv(X'*X)*X'*Y;
end


f=@(l) [ones(1,size(l)(1))' l(:,1:4) l(:,1:4).^2 l(:,1).*l(:,2) l(:,1).*l(:,3) l(:,1).*l(:,4) l(:,2).*l(:,3) l(:,2).*l(:,4) l(:,3).*l(:,4)];
Xtrainwo=f(train_data);
Xtestwo=f(test_data);

out=regre(Xtrainwo,train_data(:,5));
Xtrainout=Xtrainwo*out;

Xtestout=Xtestwo*out;

miscount = sum( xor( Xtrainout<0, train_data(:,5)<0 ));
Ein= miscount/110;
miscount = sum(xor( Xtestout<0, test_data(:,5)<0 ));
Eout= miscount/41;

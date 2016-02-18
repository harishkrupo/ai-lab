close all;
clear all;

function [w] =batchGradDesc(x,y,winit,lr)
    w=winit;

    for k=1:1000
    % while(sum(xor( (w*test_data')'<0,test_data(:,5)<0))/40 > 0.1)

        for j = 1:5
            ou=w*x';
            intm=1+e.^-ou;
            h=1./intm;
            %w(j) = w(j) + lr * ( x(i,j)* (   y(i)*(1-(1/intm))   -     (1-y(i))*(1/intm) )) ;
            v=( x(:,j).* (y - h'));
            for i=1:82
                w(j) = w(j) + lr * v(i);
            end
        end
    end
end


complete_data=csvread('ex2data2.txt');
% f=randperm(118);
train_data = complete_data(1:82,:);
test_data = complete_data(83:118,:);

x=[ones(1,size(train_data,1))' train_data(:,1:2) train_data(:,1).^2 train_data(:,2).^2 train_data(:,1).*train_data(:,2)];
y=train_data(:,3);
x2=[ones(1,size(test_data,1))' test_data(:,1:2) test_data(:,1).^2 test_data(:,2).^2 test_data(:,1).*test_data(:,2)];
out=batchGradDesc(x,y,[0.5 0.5 0.5 0.5 0.5 0.5],0.01);
trainout=1./(1+e.^(-out*x'));
testout=1./(1+e.^(-out*x2'));
Ein = mean( (trainout>=0.5) == y');

Eout = mean( (testout>=0.5) == test_data(:,3)');


sumf=0;
% for j=1:82
%     sumf=sumf+ ( y(j) - 1/( 1+e.^(-(out*x(j,:)')) ))^2;
% end
sumf=sum( (y' - 1./(1+e.^(-(out*x')) ) ).^2 );

hold on

plot(complete_data(complete_data(:,3)==1,1), complete_data(complete_data(:,3)==1,2),'b.')
plot(complete_data(complete_data(:,3)==0,1), complete_data(complete_data(:,3)==0,2),'g.')
k=1;l=1;
u=-10:0.1:10;
v=-10:0.1:10;
outdata=[u' v'];

for k=1:size(u,2)
    for l=1:size(v,2)
        z(k,l) = [1 u(k) v(l) u(k)^2 v(l)^2 u(k)*v(l)]*out';
    end
end
contour(u, v, z, [0, 0], 'LineWidth', 2)
%surf(z);
hold off
% hold on
%
% for i=-100:100
%     sum=0;
%     w=i*ones(1,5);
%     for j=1:110
%
%         sum=sum+ (y(j) - (1+e.^(out*x(j,:)' )))^2;
%     end
%     k=k+1;l(k)=sum;
% end

close all;
clear all;

function [w] =batchGradDesc(x,y,winit,lr)
    w=winit;

    for k=1:3000
    % while(sum(xor( (w*test_data')'<0,test_data(:,5)<0))/40 > 0.1)

        for j = 1:3
            for i=1:75
                ou=w*x(i,:)';
                intm=1+e^-ou;
                h=1/intm;
                %w(j) = w(j) + lr * ( x(i,j)* (   y(i)*(1-(1/intm))   -     (1-y(i))*(1/intm) )) ;
                w(j) = w(j) + lr * ( x(i,j)* (y(i) - h) );
            end
        end
    end
end


complete_data=csvread('ex2data1.txt');
% f=randperm(118);
train_data = complete_data(1:75,:);
test_data = complete_data(76:100,:);

x=[ones(1,size(train_data,1))' train_data(:,1:2) ];
y=train_data(:,3);
x2=[ones(1,size(test_data,1))' test_data(:,1:2)];
out=batchGradDesc(x,y,[0.5 0.5 0.5 ],1);
trainout=1./(1+e.^(-out*x'));
testout=1./(1+e.^(-out*x2'));
accin = mean( (trainout>=0.5) == y');

accout = mean( (testout>=0.5) == test_data(:,3)');


sumf=0;
% for j=1:82
%     sumf=sumf+ ( y(j) - 1/( 1+e.^(-(out*x(j,:)')) ))^2;
% end
sumf=sum( (y' - 1./(1+e.^(-(out*x')) ) ).^2 );

hold on
t=0:0.01:100;
plot(complete_data(complete_data(:,3)==1,1), complete_data(complete_data(:,3)==1,2),'b.','markersize',9)
plot(complete_data(complete_data(:,3)==0,1), complete_data(complete_data(:,3)==0,2),'g.','markersize',9)

plot(t,-(out(1)+out(2)*t)/out(3),'k','linewidth',2)
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

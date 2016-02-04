close all
clear all
%rand('seed',1234)
function w = perceptron(X,Y,w_init,ite)
    w=w_init;
    for epoch = 1:ite
        for i = 1:size(X)(2)

            if(sign(w*X(i,:)') != Y(i))
                w = w + Y(i)*X(i,:);
            end
        end
    end
endfunction

function f = uniquerandom(num)

    f=zeros(1,150);
    count=0;
    while(true)
        if(count==num)
            break;
        end
        gen=randi(150,1,1);

        if(f(gen)==0)
            f(gen)=1;
            count=count+1;
        end
    end
endfunction

train_data=csvread('Iris_data_norm_train.txt');
test_data=csvread('iris_data_norm_test.txt');


complete_data=[train_data' test_data']';
%complete_data=csvread('iris-virginica');
%complete_data=csvread('iris-versicolor');
Einmin=2;
samples=[ceil(30*151/100) ceil(40*151/100) ceil(50*151/100) ceil(60*151/100) ceil(70*151/100)];
for i=samples
    fvec=uniquerandom(i);

    Xtrain=complete_data(fvec==1,:);
    Xtest=complete_data(fvec==0,:);
    Xtrainwo=[ones(1,size(Xtrain)(1))' Xtrain(:,1:4)];
    Xtestwo=[ones(1,size(Xtest)(1))' Xtest(:,1:4)];
    for it=1:12
        out=perceptron([ones(1,size(Xtrain)(1))' Xtrain(:,1:4)] ,Xtrain(:,5),[0.5 0.5 0.5 0.5 0.5],2^it);
        Xtrainout = out*Xtrainwo';
        Xtestout = out*Xtestwo';
        miscount = sum(xor( Xtrainout<0, Xtrain(:,5)'<0 ));
        Ein(it)= miscount/i;
        if(Ein(it)<Einmin)
            Einmin=Ein(it);
            Wmin=out;
        end
        miscount = sum(xor( Xtestout<0, Xtest(:,5)'<0 ));
        Eout(it)= miscount/(150-i);
    end
    figure;
    hold on;
    plot(1:12,Eout,'g');
    plot(1:12,Ein,'b');
    legend('Eout','Ein');
    lola=floor(i*100/150);
    title( strcat('Ratio : ',mat2str(lola),'-', mat2str(100-lola)) );
    hold off;
    % Ein
    % Eout
end

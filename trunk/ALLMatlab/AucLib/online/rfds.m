function [feature,rFSDs]=rfds(Stroke,iNoOfHarmonicsAnalyse)
% [newPAWPointsWOResampling]=preprocessing(c,3, 1);
% L(1)=0;
% for i=2:length(newPAWPointsWOResampling(:,1))
%     L(i)=L(i-1)+sqrt((newPAWPointsWOResampling(i,1)-newPAWPointsWOResampling(i-1,1))^2+(newPAWPointsWOResampling(i,2)-newPAWPointsWOResampling(i-1,2))^2);
% end
%[newPAWPoints]=resam(newPAWPointsWOResampling,
%(L(length(newPAWPointsWOResampling(:,1)))/200));
% c=newPAWPoints;
% c=[smooth(c(:,1)) smooth(c(:,2))];
data=Stroke;
% plot(c(:,1),c(:,2),'.')
rSecondHalfOfMirroredData(:,1) = data(length(data(:,1))-1:-1:2, 1);
rSecondHalfOfMirroredData(:,2) = data(length(data(:,1))-1:-1:2, 2);
rThisMirroredInputData = [data; rSecondHalfOfMirroredData];
rThisInputData = [data; data(1,:)];
rThisMirroredInputData = [rThisMirroredInputData; rThisMirroredInputData(1,:)];

%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
outline=rThisMirroredInputData;
% iNoOfHarmonicsAnalyse=50;
bNormaliseSizeState=1;
bNormaliseOrientationState=0;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
rTwoNPi = (1:1:iNoOfHarmonicsAnalyse)* 2 * pi;
rTwoNSqPiSq = (1:1:iNoOfHarmonicsAnalyse) .* (1:1:iNoOfHarmonicsAnalyse)* 2 * pi * pi;
	
iNoOfPoints = size(outline,1) - 1; % hence there is 1 more data point in outline than iNoOfPoints
rDeltaX = zeros(iNoOfPoints+1,1); % pre-allocate some arrays
rDeltaY = zeros(iNoOfPoints+1,1);
rDeltaT = zeros(iNoOfPoints+1,1);

for iCount = 2 : iNoOfPoints + 1
    rDeltaX(iCount-1) = outline(iCount,1) - outline(iCount-1,1);
   	rDeltaY(iCount-1) = outline(iCount,2) - outline(iCount-1,2);
end

% Calculate 'time' differences from point to point - actually distances, but we are
% carrying on the fiction of a point running around the closed figure at constant speed.	
% We are analysing the projections on to the x and y axes of this point's path around the figure
for iCount = 1 : iNoOfPoints
     rDeltaT(iCount) = sqrt((rDeltaX(iCount)^2) + (rDeltaY(iCount)^2));
% iNoOfHarmonicsAnalyse elements.
% The output FSDs will DeltaY(iCount)^2));
end
check = (rDeltaT ~= 0);  % remove zeros from rDeltaT, rDeltaX...
rDeltaT = rDeltaT(check);
rDeltaX = rDeltaX(check);
rDeltaY = rDeltaY(check);

iNoOfPoints = size(rDeltaT,1) - 1; % we have removed duplicate points 

% now sum the incremental times to get the time at any point
rTime(1) = 0;
for iCount = 2 : iNoOfPoints + 1
   rTime(iCount) = rTime(iCount - 1) + rDeltaT(iCount-1);
end

rPeriod = rTime(iNoOfPoints+1); % rPeriod defined for readability

% calculate the A-sub-0 coefficient
rSum1 = 0;
for iP = 2 : iNoOfPoints + 1 
    rSum2 = 0;
    rSum3 = 0;
    rInnerDiff = 0;
   	% calculate the partial sums - these are 0 for iCount = 1
    if iP > 1 
        for iJ = 2 : iP-1
            rSum2 = rSum2 + rDeltaX(iJ-1);
      		rSum3 = rSum3 + rDeltaT(iJ-1);
        end
            rInnerDiff = rSum2 - ((rDeltaX(iP-1) / rDeltaT(iP-1)) * rSum3);
   	end
   	rIncr1 = ((rDeltaX(iP-1) / (2*rDeltaT(iP-1)))*(rTime(iP)^2-rTime(iP-1)^2) + rInnerDiff*(rTime(iP)-rTime(iP-1)));
    rSum1 = rSum1 + rIncr1;
end   
rFSDs(1,1) = ((1 / rPeriod) * rSum1) + outline(1,1); % store A-sub-0 in output FSDs array - this array will be 4 x iNoOfHarmonicsAnalyse

% calculate the a-sub-n coefficients
for iHNo = 2 : iNoOfHarmonicsAnalyse
    rSum1 = 0;
    for iP = 1 : iNoOfPoints
        rIncr1 = (rDeltaX(iP) / rDeltaT(iP))*((cos(rTwoNPi(iHNo-1)*rTime(iP+1)/rPeriod) - cos(rTwoNPi(iHNo-1)*rTime(iP)/rPeriod)));
        rSum1 = rSum1 + rIncr1;
    end
	rFSDs(1,iHNo) = (rPeriod / rTwoNSqPiSq(iHNo-1)) * rSum1;
end % "foriHNo = 1 :..."
   
rFSDs(2,1) = 0; % there is no 0th order sine coefficient
   
% calculate the b-sub-n coefficients
for iHNo = 2 : iNoOfHarmonicsAnalyse
    rSum1 = 0;
    for iP = 1 : iNoOfPoints
        rIncr1 = (rDeltaX(iP) / rDeltaT(iP))*((sin(rTwoNPi(iHNo-1)*rTime(iP+1)/rPeriod) - sin(rTwoNPi(iHNo-1)*rTime(iP)/rPeriod)));
        rSum1 = rSum1 + rIncr1;
    end
	rFSDs(2,iHNo) = (rPeriod / rTwoNSqPiSq(iHNo-1)) * rSum1;
end % "foriHNo = 1 :..."
   
% calculate the C-sub-0 coefficient
rSum1 = 0;
for iP = 2 : iNoOfPoints + 1 
    rSum2 = 0;
    rSum3 = 0;
    rInnerDiff = 0;
    % calculate the partial sums - these are 0 for iCount = 1
    if iP > 1 
        for iJ = 2 : iP-1
            rSum2 = rSum2 + rDeltaY(iJ-1);
            rSum3 = rSum3 + rDeltaT(iJ-1);
        end
        rInnerDiff = rSum2 - ((rDeltaY(iP-1) / rDeltaT(iP-1)) * rSum3);
    end
    rIncr1 = ((rDeltaY(iP-1) / (2*rDeltaT(iP-1)))*(rTime(iP)^2-rTime(iP-1)^2) + rInnerDiff*(rTime(iP)-rTime(iP-1)));
    rSum1 = rSum1 + rIncr1;
end   
rFSDs(3,1) = ((1 / rPeriod) * rSum1) + outline(1,2); % store C-sub-0 in output FSDs array - this array will be 4 x iNoOfHarmonicsAnalyse
   
% calculate the C-sub-n coefficients
for iHNo = 2 : iNoOfHarmonicsAnalyse
    rSum1 = 0;
    for iP = 1 : iNoOfPoints
        rIncr1 = (rDeltaY(iP) / rDeltaT(iP))*((cos(rTwoNPi(iHNo-1)*rTime(iP+1)/rPeriod) - cos(rTwoNPi(iHNo-1)*rTime(iP)/rPeriod)));
        rSum1 = rSum1 + rIncr1;
    end
	rFSDs(3,iHNo) = (rPeriod / rTwoNSqPiSq(iHNo-1)) * rSum1;
end % "foriHNo = 1 :..."
   
rFSDs(4,1) = 0; % there is no 0th order sine coefficient
   
% calculate the D-sub-n coefficients
for iHNo = 2 : iNoOfHarmonicsAnalyse
    rSum1 = 0;
    for iP = 1 : iNoOfPoints
        rIncr1 = (rDeltaY(iP) / rDeltaT(iP))*((sin(rTwoNPi(iHNo-1)*rTime(iP+1)/rPeriod) - sin(rTwoNPi(iHNo-1)*rTime(iP)/rPeriod)));
        rSum1 = rSum1 + rIncr1;
    end
	rFSDs(4,iHNo) = (rPeriod / rTwoNSqPiSq(iHNo-1)) * rSum1;
end % "foriHNo = 1 :...
   
% the non-normalised coefficients are now in rFSDs
% if we want the normalised ones, this is where it happens
if (bNormaliseSizeState == 1) || (bNormaliseOrientationState == 1)
    % rTheta1 is the angle through which the starting position of the first
	% harmonic phasor must be rotated  to be aligned with the major axis of
   	% the first harmonic ellipse
    rFSDsTemp = rFSDs;
    rTheta1 = 0.5 * atan(2 * (rFSDsTemp(1,2) * rFSDsTemp(2,2) + rFSDsTemp(3,2) * rFSDsTemp(4,2)) / ... 
        (rFSDsTemp(1,2)^2 + rFSDsTemp(3,2)^2 - rFSDsTemp(2,2)^2 - rFSDsTemp(4,2)^2)); 
    rTheta2 = 0.5 * (pi + atan(2 * (rFSDsTemp(1,2) * rFSDsTemp(2,2) + rFSDsTemp(3,2) * rFSDsTemp(4,2)) / ... 
        (rFSDsTemp(1,2)^2 + rFSDsTemp(3,2)^2 - rFSDsTemp(2,2)^2 - rFSDsTemp(4,2)^2)) ); 
    x11 = rFSDsTemp(1,2)*cos(rTheta1) + rFSDsTemp(2,2)*sin(rTheta1); 
    y11 = rFSDsTemp(3,2)*cos(rTheta1) + rFSDsTemp(4,2)*sin(rTheta1); 
    axisDist1 = x11^2 + y11^2; 
    x22 = rFSDsTemp(1,2)*cos(rTheta2) + rFSDsTemp(2,2)*sin(rTheta2); 
    y22 = rFSDsTemp(3,2)*cos(rTheta2) + rFSDsTemp(4,2)*sin(rTheta2); 
    axisDist2 = x22^2 + y22^2; 
    if (axisDist2 > axisDist1) 
        rTheta1 = rTheta2; 
    end


    % calculate the partially normalised coefficients - normalised for
    % starting point
    for iHNo = 1 : iNoOfHarmonicsAnalyse
        rStarFSDs(1,iHNo) = cos((iHNo-1) * rTheta1) * rFSDsTemp(1,iHNo) + sin((iHNo-1) * rTheta1) * rFSDsTemp(2,iHNo);
        rStarFSDs(2,iHNo) = -sin((iHNo-1) * rTheta1) * rFSDsTemp(1,iHNo) + cos((iHNo-1) * rTheta1) * rFSDsTemp(2,iHNo);
	    rStarFSDs(3,iHNo) = cos((iHNo-1) * rTheta1) * rFSDsTemp(3,iHNo) + sin((iHNo-1) * rTheta1) * rFSDsTemp(4,iHNo);
   	    rStarFSDs(4,iHNo) = -sin((iHNo-1) * rTheta1) * rFSDsTemp(3,iHNo) + cos((iHNo-1) * rTheta1) * rFSDsTemp(4,iHNo);
    end % for iHNo = 1 : iNoOfHarmonicsAnalyse
      
   if (rStarFSDs(3,2)~=0) 
        if (rStarFSDs(1,2)>=0) 
            rPsi1 = atan(rStarFSDs(3,2) / rStarFSDs(1,2)); 
        else 
            rPsi1 = atan(rStarFSDs(3,2) / rStarFSDs(1,2)) + pi; 
        end 
    else 
        if (rStarFSDs(1,2)>0) 
            rPsi1 = atan(rStarFSDs(3,2) / rStarFSDs(1,2)); 
        else 
            rPsi1 = atan(rStarFSDs(3,2) / rStarFSDs(1,2)) + pi; 
        end 
    end 
    rSemiMajor = sqrt(rStarFSDs(1,2)^2 + rStarFSDs(3,2)^2); % find the semi-major axis of the first ellipse
       
    rFSDs(:,:) = rStarFSDs(:,:) ./ rSemiMajor; % if we haven't asked for normalisation of orientation, 
                                                                             % return the coefficients normalised for starting point and size   
    if bNormaliseOrientationState == 1
        % now find the orientation normalised values - return them in rFSDs
   	    for iHNo = 1 : iNoOfHarmonicsAnalyse
            rFSDsTemp(1,iHNo) = (cos(rPsi1) * rStarFSDs(1,iHNo) + sin(rPsi1) * rStarFSDs(3,iHNo)) / rSemiMajor;
       	    rFSDsTemp(2,iHNo) = (cos(rPsi1) * rStarFSDs(2,iHNo) + sin(rPsi1) * rStarFSDs(4,iHNo)) / rSemiMajor;
	        rFSDsTemp(3,iHNo) = (-sin(rPsi1) * rStarFSDs(1,iHNo) + cos(rPsi1) * rStarFSDs(3,iHNo)) / rSemiMajor;
   	        rFSDsTemp(4,iHNo) = (-sin(rPsi1) * rStarFSDs(2,iHNo) + cos(rPsi1) * rStarFSDs(4,iHNo)) / rSemiMajor;
      	end % for iHNo = 1 : iNoOfHarmonicsAnalyse
            rFSDs = rFSDsTemp; % return fully normlised coefficients
    end
end % if (bNormaliseSizeState == 1) || (bNormaliseOrientationState == 1)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
iNoOfHarmonicsReconstruct=iNoOfHarmonicsAnalyse;
iNoOfPointsReconstruct=length(rThisMirroredInputData);
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




%%%%  this is the reconstraction of code 


% iStartHarmonic = 2; % start at 2  - No.1 is just an offset and is added in later (lines 17 & 27)
% ReconnedOutline = 0;
% % reconstruct the x-projection
% for iTime = 1:iNoOfPointsReconstruct
%     rSum = 0.0;
% 	for iHNo = iStartHarmonic:iNoOfHarmonicsReconstruct
%        	rSum = rSum + (rFSDs(1,iHNo) * cos(2*(iHNo-1)*pi*iTime / iNoOfPointsReconstruct) + ...
%             rFSDs(2,iHNo) * sin(2*(iHNo-1)*pi*iTime / iNoOfPointsReconstruct));
% 	end % for iHNo = 1 : iNoOfHarmonicsReconstruct
%    	ReconnedOutline(iTime,1) = rFSDs(1,1) + rSum;
% end % for iTime = 1 : iNoOfPointsReconstruct
% 
% % reconstruct the y-projection
% for iTime = 1:iNoOfPointsReconstruct
%     rSum = 0.0;
% 	for iHNo = iStartHarmonic:iNoOfHarmonicsReconstruct
%    	      rSum = rSum + (rFSDs(3,iHNo) * cos(2*(iHNo-1)*pi*iTime / iNoOfPointsReconstruct) + ...
%           	 rFSDs(4,iHNo) * sin(2*(iHNo-1)*pi*iTime / iNoOfPointsReconstruct));
% 	end % for iHNo = 1 : iNoOfHarmonicsReconstruct
%    	ReconnedOutline(iTime,2) = rFSDs(3,1) + rSum;
% end % for iTime = 1 : iNoOfPointsReconstruct
% outln = ReconnedOutline(1:length(data(:,1)),:);
%  plot(outln(:,1),outln(:,2))

feature=[rFSDs(1,:) rFSDs(2,:) rFSDs(3,:) rFSDs(4,:)]';
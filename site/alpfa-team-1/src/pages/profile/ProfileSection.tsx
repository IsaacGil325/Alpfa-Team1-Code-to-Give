import React from 'react';

const ProfileSection: React.FC = () => {
  const profileItems = [
    { label: 'NAME', className: 'text-3xl tracking-normal leading-9' },
    { label: 'SCHOOL', className: '' },
    { label: 'MAJOR', className: '' }
  ];

  return (
    <div className="flex flex-col w-[32%] max-md:ml-0 max-md:w-full">
      <div className="flex flex-col grow mt-5 text-2xl font-medium text-center text-black whitespace-nowrap max-md:mt-10">
        {profileItems.map((item, index) => (
          <div
            key={index}
            className={`px-16 py-9 ${index > 0 ? 'mt-3.5' : 'pt-6 pb-12'} text-black rounded-xl bg-neutral-100 rotate-[0.0009694872267448093rad] max-md:px-5 ${item.className}`}
          >
            {item.label}
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProfileSection;
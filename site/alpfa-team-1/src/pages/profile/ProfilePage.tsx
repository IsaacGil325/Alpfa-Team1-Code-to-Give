import React from 'react';
import ProfileSection from './ProfileSection';
import InformationSection from './InformationSection';
import ExternalLinksSection from './ExternalLinksSection';

const ProfilePage: React.FC = () => {
  return (
    <main className="flex overflow-hidden flex-col items-center pb-14 bg-orange-100">
      <header className="flex self-stretch w-full bg-neutral-700 min-h-[98px] max-md:max-w-full" />
      <section className="px-11 pb-10 mt-7 ml-4 max-w-full bg-white rounded-xl border border-solid border-black border-opacity-20 w-[1229px] max-md:px-5">
        <div className="flex gap-5 max-md:flex-col">
          <ProfileSection />
          <div className="flex flex-col ml-5 w-[68%] max-md:ml-0 max-md:w-full">
            <div className="flex flex-col leading-none max-md:mt-10 max-md:max-w-full">
              <h2 className="self-start ml-16 text-3xl font-semibold text-center text-black max-md:ml-2.5">Profile</h2>
              <div className="flex flex-col justify-center items-center px-16 py-24 mt-5 w-full rounded-xl bg-neutral-100 font-[number:var(--sds-typography-body-font-weight-regular)] text-[color:var(--sds-color-text-brand-on-brand)] text-[length:var(--sds-typography-body-size-medium)] max-md:px-5 max-md:pb-24 max-md:max-w-full">
                <button className="overflow-hidden gap-2 self-stretch px-3 py-7 mb-0 max-w-full rounded-lg border border-solid bg-zinc-800 border-zinc-800 min-h-[71px] rotate-[0.0009694872267448093rad] w-[246px] max-md:mb-2.5">
                  Upload Resume
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section className="mt-6 ml-4 w-full max-w-[1227px] max-md:max-w-full">
        <div className="flex gap-5 max-md:flex-col">
          <InformationSection />
          <ExternalLinksSection />
        </div>
      </section>
    </main>
  );
};

export default ProfilePage;